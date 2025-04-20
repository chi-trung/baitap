package vn.myproject.usermanagement.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.myproject.usermanagement.user.model.User;

/**
 * Repository để thao tác với entity User
 * 
 * @author chi-trung
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Tìm người dùng theo họ
     * 
     * @param firstName Họ cần tìm
     * @return Danh sách người dùng có họ tương ứng
     */
    List<User> findByFirstName(String firstName);
    
    /**
     * Tìm người dùng theo tên
     * 
     * @param lastName Tên cần tìm
     * @return Danh sách người dùng có tên tương ứng
     */
    List<User> findByLastName(String lastName);
    
    /**
     * Tìm người dùng theo điểm lớn hơn hoặc bằng giá trị cho trước
     * 
     * @param mark Điểm tối thiểu
     * @return Danh sách người dùng có điểm lớn hơn hoặc bằng giá trị cho trước
     */
    @Query("SELECT u FROM User u WHERE u.mark >= :mark")
    List<User> findByMarkGreaterThanEqual(@Param("mark") Integer mark);
    
    /**
     * Tìm người dùng theo họ hoặc tên (tìm kiếm mờ)
     * 
     * @param keyword Từ khóa tìm kiếm
     * @return Danh sách người dùng có họ hoặc tên chứa từ khóa
     */
    @Query("SELECT u FROM User u WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<User> searchByName(@Param("keyword") String keyword);
}
