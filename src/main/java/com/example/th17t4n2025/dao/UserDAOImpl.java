package com.example.th17t4n2025.dao;

import com.example.th17t4n2025.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class UserDAOImpl implements UserDAO {
    // Logger cho class này
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class.getName());

    // SQL queries
    private static final String SQL_INSERT = "INSERT INTO users (id, first_name, last_name, mark) VALUES (?, ?, ?, ?)";
    private static final String SQL_INSERT_AUTO = "INSERT INTO users (first_name, last_name, mark) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM users";
    private static final String SQL_DELETE = "DELETE FROM users WHERE id = ?";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE users SET first_name = ?, last_name = ?, mark = ? WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    // RowMapper để chuyển ResultSet thành User object
    private final RowMapper<User> userRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setMark(rs.getInt("mark"));
            return user;
        }
    };

    @Autowired
    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        logger.info("UserDAOImpl đã được khởi tạo");
    }

    @Override
    public User saveUser(User user) {
        try {
            // Nếu người dùng đã nhập ID
            if (user.getId() != null) {
                logger.info("Lưu người dùng với ID: " + user.getId());
                jdbcTemplate.update(
                    SQL_INSERT,
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getMark()
                );
                return user;
            } else {
                // Nếu không nhập ID, sử dụng auto-increment
                logger.info("Lưu người dùng mới với ID tự động tạo");
                KeyHolder keyHolder = new GeneratedKeyHolder();

                jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                        SQL_INSERT_AUTO,
                        Statement.RETURN_GENERATED_KEYS
                    );
                    ps.setString(1, user.getFirstName());
                    ps.setString(2, user.getLastName());
                    ps.setInt(3, user.getMark());
                    return ps;
                }, keyHolder);

                Number key = keyHolder.getKey();
                if (key != null) {
                    user.setId(key.longValue());
                    logger.info("ID được tạo: " + user.getId());
                }
                return user;
            }
        } catch (Exception e) {
            logger.severe("Lỗi khi lưu người dùng: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            logger.info("Lấy danh sách tất cả người dùng");
            List<User> users = jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
            logger.info("Tìm thấy " + users.size() + " người dùng");
            return users;
        } catch (Exception e) {
            logger.severe("Lỗi khi lấy danh sách người dùng: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteUser(Long id) {
        try {
            logger.info("Xóa người dùng có ID: " + id);
            int rowsAffected = jdbcTemplate.update(SQL_DELETE, id);
            logger.info("Kết quả xóa: " + rowsAffected + " dòng bị ảnh hưởng");
        } catch (Exception e) {
            logger.severe("Lỗi khi xóa người dùng: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public User findUser(Long id) {
        try {
            logger.info("Tìm người dùng có ID: " + id);
            try {
                // Sử dụng queryForObject thay vì query để lấy một kết quả duy nhất
                return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, userRowMapper, id);
            } catch (EmptyResultDataAccessException e) {
                logger.info("Không tìm thấy người dùng có ID: " + id);
                return null;
            }
        } catch (Exception e) {
            if (!(e instanceof EmptyResultDataAccessException)) {
                logger.severe("Lỗi khi tìm người dùng: " + e.getMessage());
            }
            throw e;
        }
    }

    @Override
    public User updateUser(User user) {
        try {
            logger.info("Cập nhật người dùng có ID: " + user.getId());
            int rowsAffected = jdbcTemplate.update(
                SQL_UPDATE,
                user.getFirstName(),
                user.getLastName(),
                user.getMark(),
                user.getId()
            );

            logger.info("Kết quả cập nhật: " + rowsAffected + " dòng bị ảnh hưởng");
            return user;
        } catch (Exception e) {
            logger.severe("Lỗi khi cập nhật người dùng: " + e.getMessage());
            throw e;
        }
    }
}