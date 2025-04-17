package com.example.th17t4n2025.service;

import com.example.th17t4n2025.model.User;
import java.util.List;

/**
 * Interface Service layer cho User
 */
public interface IUserService {
    
    /**
     * Lưu user mới
     * @param user User cần lưu
     * @return User đã được lưu
     */
    User saveUser(User user);
    
    /**
     * Lấy tất cả user
     * @return Danh sách user
     */
    List<User> getAllUsers();
    
    /**
     * Xóa user theo id
     * @param id ID của user cần xóa
     */
    void deleteUser(Long id);
    
    /**
     * Tìm user theo id
     * @param id ID của user cần tìm
     * @return User nếu tìm thấy, null nếu không tìm thấy
     */
    User findUser(Long id);
    
    /**
     * Cập nhật thông tin user
     * @param user User với thông tin đã cập nhật
     * @return User đã được cập nhật
     */
    User updateUser(User user);
} 