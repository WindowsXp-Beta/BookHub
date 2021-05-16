package com.windowsxp.bookstore.daoimpl;

import com.windowsxp.bookstore.dao.UserDao;

import com.windowsxp.bookstore.entity.User;
import com.windowsxp.bookstore.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    UserRepository userRepository;

    @Override
    public User checkUser(String username, String password){
        return userRepository.checkUser(username,password);
    }

    @Override
    public User getUserById(Integer id){
        return userRepository.getUserById(id);
    }
}
