package com.example.th17t4n2025.controller;

import com.example.th17t4n2025.model.User;
import com.example.th17t4n2025.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    private final IUserService userService;
    
    @Autowired
    public HomeController(IUserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/users")
    public String home(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        return "home";
    }
    
    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }
    
    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }
    
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
    
    @GetMapping("/findUser/{id}")
    @ResponseBody
    public User findUser(@PathVariable Long id) {
        return userService.findUser(id);
    }
} 