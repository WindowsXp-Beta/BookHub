package com.windowsxp.bookstore.repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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

    public String getBooks() {
        List<Book> result = new ArrayList<Book>();
        result = jdbcTemplate.query(
                "SELECT	* FROM book",
                (rs, rowNum) -> {
                    return new Book(rs.getInt("id"),
                            rs.getString("isbn"),
                            rs.getString("name"),
                            rs.getString("type"),
                            rs.getString("author"),
                            rs.getDouble("price"),
                            rs.getString("description"),
                            rs.getInt("inventory"),
                            rs.getString("image"));
                }
        );
        Iterator<Book> it = result.iterator();
        ArrayList<JSONArray> booksJson = new ArrayList<JSONArray>();
        while (it.hasNext()) {
            Book book = (Book) it.next();
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add(book.getTitle());
            arrayList.add(book.getAuthor());
            arrayList.add(book.getLanguage());
            arrayList.add(book.getPublished());
            arrayList.add(book.getSales());
            booksJson.add((JSONArray)	JSONArray.toJSON(arrayList));
        }
        String booksString = JSON.toJSONString(booksJson, SerializerFeature.BrowserCompatible)
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
                            rs.getDouble("price"),
                            rs.getString("description"),
                            rs.getInt("inventory"),
                            rs.getString("image"));
                }
        );
        Iterator<Book> it = result.iterator();
        return it.next();
    }
}