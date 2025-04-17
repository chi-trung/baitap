package com.example.th17t4n2025.dao;

import com.example.th17t4n2025.model.User;
import java.util.List;

/**
 * Interface UserDAO định nghĩa các thao tác với dữ liệu User
 */
public interface UserDAO {
    
    /**
     * Lưu một user vào cơ sở dữ liệu
     * @param user User cần lưu
     * @return User đã được lưu (có id)
     */
    User saveUser(User user);
    
    /**
     * Lấy tất cả user từ cơ sở dữ liệu
     * @return Danh sách các user
     */
    List<User> getAllUsers();
    
    /**
     * Xóa user theo id
     * @param id Id của user cần xóa
     */
    void deleteUser(Long id);
    
    /**
     * Tìm user theo id
     * @param id Id của user cần tìm
     * @return User nếu tìm thấy, null nếu không tìm thấy
     */
    User findUser(Long id);
    
    /**
     * Cập nhật thông tin user
     * @param user User với thông tin mới
     * @return User đã được cập nhật
     */
    User updateUser(User user);
} 