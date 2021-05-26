package com.windowsxp.bookstore.dao;

import com.windowsxp.bookstore.entity.Order;
import com.windowsxp.bookstore.entity.OrderItem;

import java.util.List;

public interface OrderDao {

    void addOrder(Order order);
    void addOrderItem(OrderItem orderItem);
}
