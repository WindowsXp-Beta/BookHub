package com.windowsxp.bookstore.repository;

import com.windowsxp.bookstore.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public User checkUser(String username, String password) {
        List<User> result = new ArrayList<User>();
        String sql = String.format("SELECT * FROM user WHERE username = \"%s\" and password = \"%s\"",username,password);
        result = jdbcTemplate.query(
                sql,(rs, rowNum) -> {
                    return new User(rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getInt("user_type"),
                            rs.getString("nickname"),
                            rs.getString("tel"),
                            rs.getString("address"));
                }
            );
        Iterator<User> it = result.iterator();
        return it.next();
    }

    public User getUserById(Integer id) {
        List<User> result = new ArrayList<User>();
        String sql = String.format("SELECT * FROM user WHERE user_id = \"%d\"",id);
        result = jdbcTemplate.query(
                sql, (rs, rowNum) -> new User(rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("user_type"),
                        rs.getString("nickname"),
                        rs.getString("tsl"),
                        rs.getString("address"))
            );
        Iterator<User> it = result.iterator();
        return it.next();
    }
}