package com.windowsxp.bookstore.daoimpl;

import com.windowsxp.bookstore.dao.UserDao;

import com.windowsxp.bookstore.entity.User;
import com.windowsxp.bookstore.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    UserRepository userRepository;

    @Override
    public User checkUser(String username, String password){
        return userRepository.checkUser(username,password);
    }

    @Override
    public User getUser(Integer id){
        return userRepository.getById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void addUser(User user) {
        userRepository.saveAndFlush(user);
    }
}