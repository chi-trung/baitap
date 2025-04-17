package com.example.th17t4n2025;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Th17T4N2025Application {

    public static void main(String[] args) {
        SpringApplication.run(Th17T4N2025Application.class, args);
        System.out.println("Ứng dụng đã khởi động thành công!");
        System.out.println("Truy cập: http://localhost:8080 để sử dụng ứng dụng");
        System.out.println("Truy cập: http://localhost:8080/db-manager để quản lý cơ sở dữ liệu");
    }

}
