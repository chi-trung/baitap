package vn.myproject.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Lớp chính để khởi động ứng dụng Spring Boot
 * 
 * @author chi-trung
 * @version 1.0
 */
@SpringBootApplication
public class UserManagementApplication {

    /**
     * Phương thức main để khởi động ứng dụng
     * 
     * @param args Tham số dòng lệnh
     */
    public static void main(String[] args) {
        // Khởi động ứng dụng và lưu context
        ConfigurableApplicationContext context = SpringApplication.run(UserManagementApplication.class, args);
        
        // Hiển thị thông tin khởi động
        displayStartupInfo();
    }
    
    /**
     * Hiển thị thông tin khi ứng dụng khởi động
     */
    private static void displayStartupInfo() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("\t\tHỆ THỐNG QUẢN LÝ NGƯỜI DÙNG");
        System.out.println("\t\t     Phiên bản 1.0");
        System.out.println("=".repeat(60));
        System.out.println("✅ Ứng dụng đã khởi động thành công!");
        System.out.println("🌐 Truy cập: http://localhost:8080 để sử dụng ứng dụng");
        System.out.println("🔧 Truy cập: http://localhost:8080/database để quản lý cơ sở dữ liệu");
        System.out.println("=".repeat(60) + "\n");
    }
}
