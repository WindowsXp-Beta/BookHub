package com.windowsxp.bookhub.backend.dao;

import com.windowsxp.bookhub.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> checkUser(String username);
    User getUser(Integer id);
    List<User.Username> getAllUsername();
    Page<User> getAllUsers(Pageable pageable);
    void deleteUser(Integer userId);
    void saveUser(User user);
    void saveAndFlushUser(User user);
}
