package com.example.th17t4n2025.controller;

import com.example.th17t4n2025.model.User;
import com.example.th17t4n2025.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Controller cho trang quản lý cơ sở dữ liệu bằng tiếng Việt
 * Thay thế cho H2 Console mặc định
 */
@Controller
@RequestMapping("/db-manager")
public class DbManagerController {

    private final IUserService userService;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DbManagerController(IUserService userService, JdbcTemplate jdbcTemplate) {
        this.userService = userService;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Hiển thị trang chính quản lý cơ sở dữ liệu
     */
    @GetMapping
    public String showDatabaseManager(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "db-manager";
    }

    /**
     * Xử lý truy vấn SQL từ form
     */
    @PostMapping("/query")
    public String executeQuery(@RequestParam String sqlQuery, Model model) {
        try {
            if (sqlQuery.trim().toLowerCase().startsWith("select")) {
                // Truy vấn SELECT
                List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlQuery);
                
                if (!rows.isEmpty()) {
                    // Lấy tên các cột từ kết quả đầu tiên
                    List<String> columns = new ArrayList<>(rows.get(0).keySet());
                    
                    // Chuyển đổi dữ liệu sang định dạng dễ hiển thị
                    List<List<Object>> queryResult = new ArrayList<>();
                    for (Map<String, Object> row : rows) {
                        List<Object> rowData = new ArrayList<>();
                        for (String column : columns) {
                            rowData.add(row.get(column));
                        }
                        queryResult.add(rowData);
                    }
                    
                    model.addAttribute("columns", columns);
                    model.addAttribute("queryResult", queryResult);
                } else {
                    model.addAttribute("message", "Truy vấn không trả về dữ liệu");
                }
            } else {
                // Truy vấn không phải SELECT (UPDATE, DELETE, INSERT)
                int rowsAffected = jdbcTemplate.update(sqlQuery);
                model.addAttribute("message", "Thực thi thành công: " + rowsAffected + " dòng bị ảnh hưởng");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi thực thi truy vấn: " + e.getMessage());
        }
        
        // Tải lại danh sách người dùng sau khi thực thi truy vấn
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        
        return "db-manager";
    }

    /**
     * Hiển thị form để thêm mới người dùng
     */
    @GetMapping("/new")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("isNew", true);
        return "user-form";
    }

    /**
     * Hiển thị form để chỉnh sửa người dùng
     */
    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        User user = userService.findUser(id);
        model.addAttribute("user", user);
        model.addAttribute("isNew", false);
        return "user-form";
    }

    /**
     * Lưu người dùng (thêm mới hoặc cập nhật)
     */
    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        if (user.getId() == null) {
            userService.saveUser(user);
        } else {
            userService.updateUser(user);
        }
        return "redirect:/db-manager";
    }

    /**
     * Xóa người dùng
     */
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/db-manager";
    }
} 