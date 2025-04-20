package vn.myproject.usermanagement.user.service;

import java.util.List;

import vn.myproject.usermanagement.user.model.User;

/**
 * Interface định nghĩa các dịch vụ liên quan đến User
 * 
 * @author chi-trung
 * @version 1.0
 */
public interface UserService {
    
    /**
     * Lấy tất cả người dùng
     * 
     * @return Danh sách tất cả người dùng
     */
    List<User> getAllUsers();
    
    /**
     * Tìm người dùng theo ID
     * 
     * @param id ID của người dùng cần tìm
     * @return Người dùng nếu tìm thấy, null nếu không tìm thấy
     */
    User getUserById(Long id);
    
    /**
     * Lưu người dùng mới
     * 
     * @param user Người dùng cần lưu
     * @return Người dùng đã được lưu
     */
    User saveUser(User user);
    
    /**
     * Cập nhật thông tin người dùng
     * 
     * @param user Người dùng với thông tin đã cập nhật
     * @return Người dùng đã được cập nhật
     */
    User updateUser(User user);
    
    /**
     * Xóa người dùng theo ID
     * 
     * @param id ID của người dùng cần xóa
     */
    void deleteUser(Long id);
    
    /**
     * Tìm kiếm người dùng theo tên
     * 
     * @param keyword Từ khóa tìm kiếm
     * @return Danh sách người dùng phù hợp
     */
    List<User> searchUsersByName(String keyword);
}
