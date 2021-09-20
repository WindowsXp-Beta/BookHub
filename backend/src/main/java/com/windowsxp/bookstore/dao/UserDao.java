package com.windowsxp.bookstore.dao;

import com.windowsxp.bookstore.entity.User;
import com.windowsxp.bookstore.entity.User.Username;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> checkUser(String username, String password);
    User getUser(Integer id);
    List<Username> getAllUsername();
    Page<User> getAllUsers(Pageable pageable);
    void deleteUser(Integer userId);
    void saveUser(User user);
    void saveAndFlushUser(User user);
}
