package vn.myproject.usermanagement.user.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.myproject.usermanagement.user.model.User;
import vn.myproject.usermanagement.user.service.UserService;

/**
 * Controller xử lý các yêu cầu liên quan đến User
 * 
 * @author chi-trung
 * @version 1.0
 */
@Controller
@RequestMapping("/users")
public class UserController {
    
    private static final Logger logger = Logger.getLogger(UserController.class.getName());
    
    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        logger.info("UserController đã được khởi tạo");
    }
    
    /**
     * Hiển thị trang quản lý người dùng
     * 
     * @param model Model để truyền dữ liệu đến view
     * @return Tên view
     */
    @GetMapping
    public String showUserManagementPage(Model model) {
        logger.info("Hiển thị trang quản lý người dùng");
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        return "user-management";
    }
    
    /**
     * Tìm người dùng theo ID
     * 
     * @param id ID của người dùng cần tìm
     * @return Người dùng dưới dạng JSON
     */
    @GetMapping("/{id}")
    @ResponseBody
    public User getUserById(@PathVariable Long id) {
        logger.info("API: Lấy người dùng có ID: " + id);
        return userService.getUserById(id);
    }
    
    /**
     * Thêm người dùng mới
     * 
     * @param user Người dùng cần thêm
     * @return Chuyển hướng đến trang quản lý người dùng
     */
    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        logger.info("Thêm người dùng mới: " + user.getFullName());
        userService.saveUser(user);
        return "redirect:/users";
    }
    
    /**
     * Cập nhật thông tin người dùng
     * 
     * @param user Người dùng với thông tin đã cập nhật
     * @return Chuyển hướng đến trang quản lý người dùng
     */
    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        logger.info("Cập nhật người dùng có ID: " + user.getId());
        userService.updateUser(user);
        return "redirect:/users";
    }
    
    /**
     * Xóa người dùng
     * 
     * @param id ID của người dùng cần xóa
     * @return Chuyển hướng đến trang quản lý người dùng
     */
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        logger.info("Xóa người dùng có ID: " + id);
        userService.deleteUser(id);
        return "redirect:/users";
    }
    
    /**
     * Tìm kiếm người dùng theo tên
     * 
     * @param keyword Từ khóa tìm kiếm
     * @param model Model để truyền dữ liệu đến view
     * @return Tên view
     */
    @GetMapping("/search")
    public String searchUsers(@RequestParam(required = false) String keyword, Model model) {
        logger.info("Tìm kiếm người dùng với từ khóa: " + keyword);
        List<User> users;
        
        if (keyword != null && !keyword.trim().isEmpty()) {
            users = userService.searchUsersByName(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            users = userService.getAllUsers();
        }
        
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        return "user-management";
    }
}
