package com.example.th17t4n2025.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    private String major;
    private double gpa;
    
    // Constructor không có id để sử dụng khi tạo mới
    public Student(String name, String email, String major, double gpa) {
        this.name = name;
        this.email = email;
        this.major = major;
        this.gpa = gpa;
    }
} 