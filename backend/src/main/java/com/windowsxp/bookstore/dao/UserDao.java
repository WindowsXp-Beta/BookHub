package com.windowsxp.bookstore.dao;

import com.windowsxp.bookstore.entity.User;

import java.util.List;

public interface UserDao {
    User checkUser(String username, String password);
    User getUser(Integer id);
    List<User> findAllUsers();
    void deleteUser(Integer userId);
    void addUser(User user);
}
