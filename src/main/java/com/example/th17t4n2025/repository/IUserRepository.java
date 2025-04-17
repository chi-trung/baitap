package com.example.th17t4n2025.repository;

import com.example.th17t4n2025.model.User;
import java.util.List;

/**
 * Interface Repository layer cho User
 */
public interface IUserRepository {
    
    /**
     * Lưu user vào cơ sở dữ liệu
     * @param user User cần lưu
     * @return User đã được lưu
     */
    User save(User user);
    
    /**
     * Lấy tất cả user từ cơ sở dữ liệu
     * @return Danh sách user
     */
    List<User> findAll();
    
    /**
     * Xóa user theo id
     * @param id ID của user cần xóa
     */
    void deleteById(Long id);
    
    /**
     * Tìm user theo id
     * @param id ID của user cần tìm
     * @return User nếu tìm thấy, null nếu không tìm thấy
     */
    User findById(Long id);
    
    /**
     * Cập nhật thông tin user
     * @param user User với thông tin đã cập nhật
     * @return User đã được cập nhật
     */
    User update(User user);
} 