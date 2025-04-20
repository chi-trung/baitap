package vn.myproject.usermanagement.user.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.myproject.usermanagement.user.model.User;
import vn.myproject.usermanagement.user.repository.UserRepository;

/**
 * Triển khai các dịch vụ liên quan đến User
 * 
 * @author chi-trung
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    
    private final UserRepository userRepository;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        logger.info("UserServiceImpl đã được khởi tạo");
    }

    @Override
    public List<User> getAllUsers() {
        logger.info("Lấy danh sách tất cả người dùng");
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        logger.info("Tìm người dùng có ID: " + id);
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        logger.info("Lưu người dùng mới: " + user.getFullName());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        logger.info("Cập nhật người dùng có ID: " + user.getId());
        
        // Kiểm tra xem người dùng có tồn tại không
        if (userRepository.existsById(user.getId())) {
            return userRepository.save(user);
        } else {
            logger.warning("Không tìm thấy người dùng có ID: " + user.getId());
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        logger.info("Xóa người dùng có ID: " + id);
        userRepository.deleteById(id);
    }

    @Override
    public List<User> searchUsersByName(String keyword) {
        logger.info("Tìm kiếm người dùng với từ khóa: " + keyword);
        return userRepository.searchByName(keyword);
    }
}
