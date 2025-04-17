package com.example.th17t4n2025.service;

import com.example.th17t4n2025.entity.Student;
import com.example.th17t4n2025.repository.jdbc.StudentJdbcRepository;
import com.example.th17t4n2025.repository.jpa.StudentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentJpaRepository studentJpaRepository;
    private final StudentJdbcRepository studentJdbcRepository;
    
    @Autowired
    public StudentService(StudentJpaRepository studentJpaRepository, StudentJdbcRepository studentJdbcRepository) {
        this.studentJpaRepository = studentJpaRepository;
        this.studentJdbcRepository = studentJdbcRepository;
    }
    
    // Các phương thức sử dụng JPA Repository
    
    @Transactional(readOnly = true)
    public List<Student> getAllStudentsJpa() {
        return studentJpaRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Student> getStudentByIdJpa(Long id) {
        return studentJpaRepository.findById(id);
    }
    
    @Transactional
    public Student saveStudentJpa(Student student) {
        return studentJpaRepository.save(student);
    }
    
    @Transactional
    public void deleteStudentJpa(Long id) {
        studentJpaRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public List<Student> findStudentsByNameJpa(String name) {
        return studentJpaRepository.findByNameContainingIgnoreCase(name);
    }
    
    @Transactional(readOnly = true)
    public List<Student> findStudentsByMajorJpa(String major) {
        return studentJpaRepository.findByMajor(major);
    }
    
    @Transactional(readOnly = true)
    public List<Student> findStudentsByMinGpaJpa(double minGpa) {
        return studentJpaRepository.findByGpaGreaterThanEqual(minGpa);
    }
    
    @Transactional(readOnly = true)
    public List<Student> findTopStudentsByGpaJpa(double minGpa) {
        return studentJpaRepository.findTopStudentsByGpa(minGpa);
    }
    
    // Các phương thức sử dụng JDBC Repository
    
    public List<Student> getAllStudentsJdbc() {
        return studentJdbcRepository.findAll();
    }
    
    public Optional<Student> getStudentByIdJdbc(Long id) {
        return studentJdbcRepository.findById(id);
    }
    
    public Student saveStudentJdbc(Student student) {
        return studentJdbcRepository.save(student);
    }
    
    public void deleteStudentJdbc(Long id) {
        studentJdbcRepository.deleteById(id);
    }
    
    public List<Student> findStudentsByNameJdbc(String name) {
        return studentJdbcRepository.findByNameContainingIgnoreCase(name);
    }
    
    public List<Student> findStudentsByMajorJdbc(String major) {
        return studentJdbcRepository.findByMajor(major);
    }
    
    public List<Student> findStudentsByMinGpaJdbc(double minGpa) {
        return studentJdbcRepository.findByGpaGreaterThanEqual(minGpa);
    }
    
    public List<Student> findTopStudentsByGpaJdbc(double minGpa) {
        return studentJdbcRepository.findTopStudentsByGpa(minGpa);
    }
} 