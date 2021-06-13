package com.windowsxp.bookstore.service;

import com.alibaba.fastjson.JSONObject;
import com.windowsxp.bookstore.entity.Order;

import java.util.List;


public interface OrderService {
    List<Order> getOrders(Integer userId);
    List<Order> getAllOrders();
    void addOrder(JSONObject params);
}