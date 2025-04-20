package com.example.th17t4n2025.dao;

import com.example.th17t4n2025.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setMark(rs.getInt("mark"));
        return user;
    };

    @Autowired
    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User saveUser(User user) {
        // Nếu người dùng đã nhập ID
        if (user.getId() != null) {
            jdbcTemplate.update(
                "INSERT INTO users (id, first_name, last_name, mark) VALUES (?, ?, ?, ?)",
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getMark()
            );
            return user;
        } else {
            // Nếu không nhập ID, sử dụng auto-increment
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO users (first_name, last_name, mark) VALUES (?, ?, ?)",
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
            }
            return user;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM users", userRowMapper);
    }

    @Override
    public void deleteUser(Long id) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }

    @Override
    public User findUser(Long id) {
        List<User> users = jdbcTemplate.query(
            "SELECT * FROM users WHERE id = ?",
            userRowMapper,
            id
        );

        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User updateUser(User user) {
        jdbcTemplate.update(
            "UPDATE users SET first_name = ?, last_name = ?, mark = ? WHERE id = ?",
            user.getFirstName(),
            user.getLastName(),
            user.getMark(),
            user.getId()
        );

        return user;
    }
}