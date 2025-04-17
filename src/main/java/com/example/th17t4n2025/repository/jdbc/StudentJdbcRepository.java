package com.example.th17t4n2025.repository.jdbc;

import com.example.th17t4n2025.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentJdbcRepository {

    private final JdbcTemplate jdbcTemplate;
    
    // RowMapper để chuyển đổi ResultSet thành đối tượng Student
    private final RowMapper<Student> studentRowMapper = (rs, rowNum) -> {
        Student student = new Student();
        student.setId(rs.getLong("id"));
        student.setName(rs.getString("name"));
        student.setEmail(rs.getString("email"));
        student.setMajor(rs.getString("major"));
        student.setGpa(rs.getDouble("gpa"));
        return student;
    };

    @Autowired
    public StudentJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Lấy tất cả sinh viên
    public List<Student> findAll() {
        String sql = "SELECT * FROM students";
        return jdbcTemplate.query(sql, studentRowMapper);
    }

    // Tìm sinh viên theo ID
    public Optional<Student> findById(Long id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        List<Student> students = jdbcTemplate.query(sql, studentRowMapper, id);
        return students.isEmpty() ? Optional.empty() : Optional.of(students.get(0));
    }

    // Thêm sinh viên mới
    public Student save(Student student) {
        if (student.getId() == null) {
            return insert(student);
        } else {
            return update(student);
        }
    }

    // Thêm sinh viên mới và lấy id được tạo tự động
    private Student insert(Student student) {
        String sql = "INSERT INTO students (name, email, major, gpa) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getMajor());
            ps.setDouble(4, student.getGpa());
            return ps;
        }, keyHolder);
        
        student.setId(keyHolder.getKey().longValue());
        return student;
    }

    // Cập nhật sinh viên hiện có
    private Student update(Student student) {
        String sql = "UPDATE students SET name = ?, email = ?, major = ?, gpa = ? WHERE id = ?";
        jdbcTemplate.update(sql, student.getName(), student.getEmail(), student.getMajor(), student.getGpa(), student.getId());
        return student;
    }

    // Xóa sinh viên theo ID
    public void deleteById(Long id) {
        String sql = "DELETE FROM students WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // Tìm sinh viên theo tên (tìm kiếm không phân biệt chữ hoa/thường)
    public List<Student> findByNameContainingIgnoreCase(String name) {
        String sql = "SELECT * FROM students WHERE LOWER(name) LIKE LOWER(?)";
        return jdbcTemplate.query(sql, studentRowMapper, "%" + name + "%");
    }

    // Tìm sinh viên theo ngành học
    public List<Student> findByMajor(String major) {
        String sql = "SELECT * FROM students WHERE major = ?";
        return jdbcTemplate.query(sql, studentRowMapper, major);
    }

    // Tìm sinh viên có GPA lớn hơn hoặc bằng một giá trị cụ thể
    public List<Student> findByGpaGreaterThanEqual(double minGpa) {
        String sql = "SELECT * FROM students WHERE gpa >= ?";
        return jdbcTemplate.query(sql, studentRowMapper, minGpa);
    }

    // Lấy danh sách sinh viên có GPA cao nhất
    public List<Student> findTopStudentsByGpa(double minGpa) {
        String sql = "SELECT * FROM students WHERE gpa > ? ORDER BY gpa DESC";
        return jdbcTemplate.query(sql, studentRowMapper, minGpa);
    }
} 