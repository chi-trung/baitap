package com.example.th17t4n2025;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Th17T4N2025Application {

    public static void main(String[] args) {
        // Khởi động ứng dụng Spring Boot
        SpringApplication.run(Th17T4N2025Application.class, args);

        // Hiển thị thông tin khởi động
        printStartupInfo();
    }

    /**
     * Hiển thị thông tin khi ứng dụng khởi động
     */
    private static void printStartupInfo() {
        String separator = "==".repeat(30);
        System.out.println(separator);
        System.out.println("\tỨNG DỤNG QUẢN LÝ NGƯỜI DÙNG ĐÃ KHỜI ĐỘNG");
        System.out.println(separator);
        System.out.println(">> Truy cập: http://localhost:8080 để sử dụng ứng dụng");
        System.out.println(">> Truy cập: http://localhost:8080/db-manager để quản lý cơ sở dữ liệu");
        System.out.println(separator);
    }

}
