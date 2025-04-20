-- Tạo bảng users nếu chưa tồn tại
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    mark INT
);

-- Xóa dữ liệu cũ (nếu có)
TRUNCATE TABLE users;

-- Thêm dữ liệu mẫu
INSERT INTO users (first_name, last_name, mark) VALUES
    ('Nguyễn', 'Văn A', 8),
    ('Trần', 'Thị B', 9),
    ('Lê', 'Văn C', 7),
    ('Phạm', 'Thị D', 10);

-- Log thông báo
SELECT 'Đã khởi tạo cơ sở dữ liệu thành công!' as message;
