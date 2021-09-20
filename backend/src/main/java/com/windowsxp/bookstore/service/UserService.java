package com.windowsxp.bookstore.service;

import com.windowsxp.bookstore.entity.User;
import com.windowsxp.bookstore.enumerate.UserType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User login(String username, String password);
    User getUser(Integer id);
    Page<User> findAllUsers(Pageable pageable);
    Boolean checkDuplicateUsername(String newUsername);
    void deleteUser(Integer userId);
    void editUser(Integer userId, UserType userType);
    User register(String username, String rawPassword, String email, String address);
}