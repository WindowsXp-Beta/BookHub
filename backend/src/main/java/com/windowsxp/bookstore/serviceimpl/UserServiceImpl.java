package com.windowsxp.bookstore.serviceimpl;

import com.windowsxp.bookstore.dao.UserDao;
import com.windowsxp.bookstore.entity.User;
import com.windowsxp.bookstore.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password){
        return userDao.checkUser(username,password);
    }

    @Override
    public User getUserById(Integer id){
        return userDao.getUserById(id);
    }

}
