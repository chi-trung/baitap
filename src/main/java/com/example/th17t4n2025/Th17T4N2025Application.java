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
        String separator = "==".repeat(50);
        System.out.println("\n" + separator);
        System.out.println("\tỨNG DỤNG QUẢN LÝ NGƯỜI DÙNG ĐÃ KHỜI ĐỘNG THÀNH CÔNG!");
        System.out.println(separator);
        System.out.println("\n✨ Truy cập ứng dụng tại: http://localhost:8080");
        System.out.println(separator + "\n");
    }

}
