package com.windowsxp.bookstore.dao;

import com.alibaba.fastjson.JSONObject;
import com.windowsxp.bookstore.entity.Order;

import java.util.List;

public interface OrderDao {
    void addOrder(JSONObject params);

    List<Order> getAllOrders(Integer userId);
}
