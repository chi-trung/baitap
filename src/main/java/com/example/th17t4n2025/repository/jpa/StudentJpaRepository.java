package com.example.th17t4n2025.repository.jpa;

import com.example.th17t4n2025.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentJpaRepository extends JpaRepository<Student, Long> {
    
    // Các phương thức truy vấn cơ bản được tự động cung cấp bởi JpaRepository
    
    // Tìm kiếm sinh viên theo tên
    List<Student> findByNameContainingIgnoreCase(String name);
    
    // Tìm kiếm sinh viên theo ngành học
    List<Student> findByMajor(String major);
    
    // Tìm kiếm sinh viên có GPA lớn hơn một giá trị cụ thể
    List<Student> findByGpaGreaterThanEqual(double minGpa);
    
    // Sử dụng JPQL để viết truy vấn tùy chỉnh
    @Query("SELECT s FROM Student s WHERE s.gpa > :minGpa ORDER BY s.gpa DESC")
    List<Student> findTopStudentsByGpa(double minGpa);
} 