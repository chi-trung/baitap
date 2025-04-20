# Ứng dụng Quản lý Người dùng - TH17T4N2025

Đây là một ứng dụng Spring Boot đơn giản để quản lý thông tin người dùng, được phát triển bởi chi-trung.

## Tính năng

- Xem danh sách người dùng
- Thêm người dùng mới (có thể nhập ID tùy chọn)
- Cập nhật thông tin người dùng
- Xóa người dùng
- Tìm kiếm người dùng theo tên

## Công nghệ sử dụng

- Spring Boot 3.1.0
- Spring Data JPA
- H2 Database (in-memory)
- Thymeleaf
- Bootstrap 5
- Font Awesome
- Lombok

## Cấu trúc dự án

Dự án được tổ chức theo tính năng (feature-based) thay vì theo lớp (layer-based):

```
src/main/java/vn/myproject/usermanagement/
├── UserManagementApplication.java
├── config/
│   ├── DatabaseConfig.java
│   └── WebConfig.java
├── user/
│   ├── controller/
│   │   └── UserController.java
│   ├── model/
│   │   └── User.java
│   ├── repository/
│   │   └── UserRepository.java
│   └── service/
│       ├── UserService.java
│       └── UserServiceImpl.java
└── common/
    └── ...
```

## Cách chạy ứng dụng

### Yêu cầu

- Java 17 hoặc cao hơn
- Maven 3.6 hoặc cao hơn

### Các bước thực hiện

1. Clone repository này
2. Di chuyển vào thư mục dự án
3. Chạy lệnh: `mvn spring-boot:run`
4. Truy cập: http://localhost:8080

## Cấu hình

Các cấu hình chính của ứng dụng nằm trong file `application.properties`:

- Cơ sở dữ liệu H2 (in-memory)
- Cổng máy chủ: 8080
- Console H2: http://localhost:8080/database

## Tác giả

- **chi-trung** - *Phát triển ban đầu*
