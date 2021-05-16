package com.windowsxp.bookstore.repository;

import com.windowsxp.bookstore.entity.Book;
import com.windowsxp.bookstore.entity.CartItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CartRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<CartItem> getCart(Integer userId) {
        List<CartItem> result = new ArrayList<CartItem>();
        String sql = String.format("SELECT * FROM cart_item c, book b WHERE c.book_id = b.id and user_id = \"%d\"",userId);
        result = jdbcTemplate.query(
                sql,(rs, rowNum) -> new CartItem(
                        rs.getInt("item_id"),
                        rs.getInt("user_id"),
                        new Book(rs.getInt("id"),
                                rs.getString("isbn"),
                                rs.getString("name"),
                                rs.getString("type"),
                                rs.getString("author"),
                                rs.getInt("price"),
                                rs.getString("description"),
                                rs.getInt("inventory"),
                                rs.getString("image")
                        ),
                        rs.getInt("book_number")
                )
        );
        return result;
    }

    public void addCart(Map<String, String> params) {
        String sql = String.format("INSERT INTO cart_item (user_id,book_id,book_number) values (?,?,?) on duplicate key update book_number = %d",Integer.valueOf(params.get("bookNumber")));
        jdbcTemplate.update(
                sql,Integer.valueOf(params.get("userId")),
                Integer.valueOf(params.get("bookId")),
                Integer.valueOf(params.get("bookNumber"))
        );
    }

    public void deleteCart(Map<String, String> params) {
        String sql = String.format("DELETE FROM cart_item WHERE item_id=%d",Integer.valueOf(params.get("itemId")));
        jdbcTemplate.update(sql);
    }

    public void editCartItemNum(Map<String, String> params) {
        String sql = String.format("UPDATE cart_item set book_number=%d where item_id=%d",Integer.valueOf(params.get("bookNum")),Integer.valueOf(params.get("itemId")));
        jdbcTemplate.update(sql);
    }
}