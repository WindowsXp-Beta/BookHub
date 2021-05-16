package com.windowsxp.bookstore.repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.windowsxp.bookstore.entity.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class BookRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Book> getBooks() {
        List<Book> result = new ArrayList<Book>();
        result = jdbcTemplate.query(
                "SELECT	* FROM book",
                (rs, rowNum) -> {
                    return new Book(rs.getInt("id"),
                            rs.getString("isbn"),
                            rs.getString("name"),
                            rs.getString("type"),
                            rs.getString("author"),
                            rs.getInt("price"),
                            rs.getString("description"),
                            rs.getInt("inventory"),
                            rs.getString("image"));
                }
        );
        Iterator<Book> it = result.iterator();
        return result;
    }

    public Book getOne(Integer id) {
        List<Book> result = new ArrayList<Book>();
        String sql = String.format("SELECT * FROM book WHERE id = \"%d\"", id);
        result = jdbcTemplate.query(
                sql,(rs, rowNum) -> {
                    return new Book(rs.getInt("id"),
                            rs.getString("isbn"),
                            rs.getString("name"),
                            rs.getString("type"),
                            rs.getString("author"),
                            rs.getInt("price"),
                            rs.getString("description"),
                            rs.getInt("inventory"),
                            rs.getString("image"));
                }
        );
        Iterator<Book> it = result.iterator();
        return it.next();
    }
}