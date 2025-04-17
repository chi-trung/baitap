package com.example.th17t4n2025.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/*
 * Class này đã được vô hiệu hóa vì không cần thiết cho ứng dụng quản lý User.
 * Dữ liệu sinh viên (Student) không được sử dụng trong phạm vi bài tập hiện tại.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    /*
    private final StudentJpaRepository studentRepository;

    @Autowired
    public DataInitializer(StudentJpaRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    */

    // Constructor trống để Spring có thể khởi tạo
    public DataInitializer() {
    }
    
    @Override
    public void run(String... args) {
        // Đã vô hiệu hóa việc khởi tạo dữ liệu Student
        System.out.println("Data initialization skipped: Student data is not required for this application.");
        
        /*
        List<Student> students = Arrays.asList(
            new Student("Nguyễn Văn A", "nguyenvana@example.com", "Computer Science", 3.8),
            new Student("Trần Thị B", "tranthib@example.com", "Business Administration", 3.6),
            new Student("Lê Văn C", "levanc@example.com", "Computer Science", 3.9),
            new Student("Phạm Thị D", "phamthid@example.com", "Engineering", 3.7),
            new Student("Hoàng Văn E", "hoangvane@example.com", "Mathematics", 4.0),
            new Student("Vũ Thị F", "vuthif@example.com", "Physics", 3.5),
            new Student("Đặng Văn G", "dangvang@example.com", "Biology", 3.2),
            new Student("Mai Thị H", "maithih@example.com", "Chemistry", 3.4),
            new Student("Bùi Văn I", "buivani@example.com", "Computer Science", 3.6),
            new Student("Đỗ Thị K", "dothik@example.com", "Engineering", 3.8)
        );

        // Lưu vào cơ sở dữ liệu
        studentRepository.saveAll(students);
        
        System.out.println("Data initialization completed: " + studentRepository.count() + " students loaded.");
        */
    }
} 