package com.example.th17t4n2025.controller;

import com.example.th17t4n2025.entity.Student;
import com.example.th17t4n2025.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // API sử dụng JPA Repository
    
    @GetMapping("/jpa")
    public List<Student> getAllStudentsJpa() {
        return studentService.getAllStudentsJpa();
    }
    
    @GetMapping("/jpa/{id}")
    public ResponseEntity<Student> getStudentByIdJpa(@PathVariable Long id) {
        return studentService.getStudentByIdJpa(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/jpa")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudentJpa(@RequestBody Student student) {
        return studentService.saveStudentJpa(student);
    }
    
    @PutMapping("/jpa/{id}")
    public ResponseEntity<Student> updateStudentJpa(@PathVariable Long id, @RequestBody Student student) {
        return studentService.getStudentByIdJpa(id)
                .map(existingStudent -> {
                    student.setId(id);
                    return ResponseEntity.ok(studentService.saveStudentJpa(student));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/jpa/{id}")
    public ResponseEntity<Void> deleteStudentJpa(@PathVariable Long id) {
        return studentService.getStudentByIdJpa(id)
                .map(student -> {
                    studentService.deleteStudentJpa(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/jpa/search/name")
    public List<Student> searchStudentsByNameJpa(@RequestParam String name) {
        return studentService.findStudentsByNameJpa(name);
    }
    
    @GetMapping("/jpa/search/major")
    public List<Student> searchStudentsByMajorJpa(@RequestParam String major) {
        return studentService.findStudentsByMajorJpa(major);
    }
    
    @GetMapping("/jpa/search/gpa")
    public List<Student> searchStudentsByGpaJpa(@RequestParam double minGpa) {
        return studentService.findStudentsByMinGpaJpa(minGpa);
    }
    
    @GetMapping("/jpa/top-students")
    public List<Student> getTopStudentsJpa(@RequestParam(defaultValue = "3.0") double minGpa) {
        return studentService.findTopStudentsByGpaJpa(minGpa);
    }
    
    // API sử dụng JDBC Repository
    
    @GetMapping("/jdbc")
    public List<Student> getAllStudentsJdbc() {
        return studentService.getAllStudentsJdbc();
    }
    
    @GetMapping("/jdbc/{id}")
    public ResponseEntity<Student> getStudentByIdJdbc(@PathVariable Long id) {
        return studentService.getStudentByIdJdbc(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/jdbc")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudentJdbc(@RequestBody Student student) {
        return studentService.saveStudentJdbc(student);
    }
    
    @PutMapping("/jdbc/{id}")
    public ResponseEntity<Student> updateStudentJdbc(@PathVariable Long id, @RequestBody Student student) {
        return studentService.getStudentByIdJdbc(id)
                .map(existingStudent -> {
                    student.setId(id);
                    return ResponseEntity.ok(studentService.saveStudentJdbc(student));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/jdbc/{id}")
    public ResponseEntity<Void> deleteStudentJdbc(@PathVariable Long id) {
        return studentService.getStudentByIdJdbc(id)
                .map(student -> {
                    studentService.deleteStudentJdbc(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/jdbc/search/name")
    public List<Student> searchStudentsByNameJdbc(@RequestParam String name) {
        return studentService.findStudentsByNameJdbc(name);
    }
    
    @GetMapping("/jdbc/search/major")
    public List<Student> searchStudentsByMajorJdbc(@RequestParam String major) {
        return studentService.findStudentsByMajorJdbc(major);
    }
    
    @GetMapping("/jdbc/search/gpa")
    public List<Student> searchStudentsByGpaJdbc(@RequestParam double minGpa) {
        return studentService.findStudentsByMinGpaJdbc(minGpa);
    }
    
    @GetMapping("/jdbc/top-students")
    public List<Student> getTopStudentsJdbc(@RequestParam(defaultValue = "3.0") double minGpa) {
        return studentService.findTopStudentsByGpaJdbc(minGpa);
    }
    
    // API để hiển thị thông tin so sánh giữa JPA và JDBC
    @GetMapping("/compare")
    public Map<String, List<Student>> compareRepos() {
        List<Student> jpaStudents = studentService.getAllStudentsJpa();
        List<Student> jdbcStudents = studentService.getAllStudentsJdbc();
        return Map.of("jpa", jpaStudents, "jdbc", jdbcStudents);
    }
} 