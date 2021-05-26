package com.windowsxp.bookstore.repository;

import com.windowsxp.bookstore.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "from User where userName = :username and password = :password")
    User checkUser(@Param("username") String username, @Param("password") String password);
}