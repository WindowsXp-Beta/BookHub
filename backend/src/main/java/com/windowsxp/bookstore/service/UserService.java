package com.windowsxp.bookstore.service;


import com.windowsxp.bookstore.entity.User;

public interface UserService {

    User checkUser(String username, String password);
    User getUserById(Integer id);

}
