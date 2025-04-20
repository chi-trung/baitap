package vn.myproject.usermanagement.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity đại diện cho người dùng trong hệ thống
 * 
 * @author chi-trung
 * @version 1.0
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;
    
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;
    
    @Column(name = "mark")
    private Integer mark;
    
    /**
     * Phương thức tiện ích để lấy tên đầy đủ của người dùng
     * 
     * @return Tên đầy đủ (họ + tên)
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    /**
     * Constructor không có id để sử dụng khi tạo mới
     * 
     * @param firstName Họ
     * @param lastName Tên
     * @param mark Điểm
     */
    public User(String firstName, String lastName, Integer mark) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mark = mark;
    }
}
