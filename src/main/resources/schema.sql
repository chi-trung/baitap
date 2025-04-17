-- Tạo bảng students
CREATE TABLE IF NOT EXISTS students (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    major VARCHAR(100),
    gpa DOUBLE
);

-- Tạo bảng users
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    mark INT
);

-- Xóa dữ liệu cũ và thêm dữ liệu mẫu
TRUNCATE TABLE users;
INSERT INTO users (first_name, last_name, mark) VALUES
    ('Lam', 'Nguyen', 7),
    ('Van', 'Nguyen', 8);

-- Chú ý: H2 sẽ tự động tạo bảng dựa trên Entity JPA, nhưng file này
-- đảm bảo cấu trúc bảng sẽ giống nhau cho cả JPA và JDBC.
-- Đồng thời, nó cũng giúp định nghĩa rõ cấu trúc bảng. 