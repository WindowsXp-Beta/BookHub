package com.windowsxp.bookstore.dao;

import com.windowsxp.bookstore.entity.User;

public interface UserDao {
    User checkUser(String username, String password);
    User getUser(Integer id);
}
