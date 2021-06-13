package com.windowsxp.bookstore.service;

import com.alibaba.fastjson.JSONObject;
import com.windowsxp.bookstore.entity.User;

import java.util.List;

public interface UserService {
    User checkUser(String username, String password);
    User getUser(Integer id);
    List<User> findAllUsers();
    void deleteUser(Integer userId);
    void addUser(User user);
}