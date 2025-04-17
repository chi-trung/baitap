package com.example.th17t4n2025.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Cấu hình web cho ứng dụng
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Thêm controller cho trang chủ
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Đảm bảo trang chủ hiển thị menu chọn
        registry.addViewController("/").setViewName("forward:/index.html");
    }
} 