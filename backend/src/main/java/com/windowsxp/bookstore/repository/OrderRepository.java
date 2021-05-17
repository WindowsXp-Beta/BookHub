package com.windowsxp.bookstore.repository;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.windowsxp.bookstore.entity.Book;
import com.windowsxp.bookstore.entity.Order;

import com.windowsxp.bookstore.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class OrderRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Order> getAllOrders(Integer userId) {
        List<Order> result = new ArrayList<Order>();
        String sql = String.format("SELECT * FROM `order` o, order_item oi,book b WHERE o.order_id = oi.order_id and o.user_id=%d and b.id = oi.book_id" ,userId);
        result = jdbcTemplate.query(
                sql,(rs, rowBum)->new Order(
                        rs.getInt("order_id"),
                        rs.getInt("user_id"),
                        rs.getTimestamp("time"),
                        new OrderItem(
                                rs.getInt("item_id"),
                                rs.getInt("order_id"),
                                new Book(rs.getInt("b.id"),
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
                )
        );
        return result;
    }

    public void addOrder(JSONObject params) {
        String sql = String.format("INSERT INTO `order` (user_id,time) values (?,?)");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Timestamp orderTime = new Timestamp(System.currentTimeMillis());
        System.out.println(orderTime);
        jdbcTemplate.update(new PreparedStatementCreator() {
                                @Override
                                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                                    ps.setInt(1, Integer.valueOf(params.get("userId").toString()));
                                    ps.setTimestamp(2, orderTime);
                                    return ps;
                                }
                            },keyHolder
                        );
        Integer orderId = keyHolder.getKey().intValue();
        JSONArray jsonArray = params.getJSONArray("orderItems");
        String sqlItem = String.format("INSERT INTO order_item (order_id,book_id,book_number) VALUES (?,?,?)");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            int bookId = Integer.parseInt(obj.get("bookId").toString());
            int number = Integer.parseInt(obj.get("bookNum").toString());
            jdbcTemplate.update(
                    sqlItem,orderId,bookId,number
            );
        }
    }
}
