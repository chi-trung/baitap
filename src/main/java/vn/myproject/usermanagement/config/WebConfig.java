package vn.myproject.usermanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Cấu hình Web MVC
 * 
 * @author chi-trung
 * @version 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Cấu hình các controller view mặc định
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Chuyển hướng trang chủ đến trang quản lý người dùng
        registry.addViewController("/").setViewName("redirect:/users");
        
        // Chuyển hướng /home đến trang quản lý người dùng
        registry.addViewController("/home").setViewName("redirect:/users");
    }
}
