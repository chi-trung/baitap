package com.example.th17t4n2025.repository;

import com.example.th17t4n2025.dao.UserDAO;
import com.example.th17t4n2025.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

    private final UserDAO userDAO;
    
    @Autowired
    public UserRepository(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User save(User user) {
        return userDAO.saveUser(user);
    }

    @Override
    public List<User> findAll() {
        return userDAO.getAllUsers();
    }

    @Override
    public void deleteById(Long id) {
        userDAO.deleteUser(id);
    }

    @Override
    public User findById(Long id) {
        return userDAO.findUser(id);
    }

    @Override
    public User update(User user) {
        return userDAO.updateUser(user);
    }
} 