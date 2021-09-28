package com.windowsxp.bookstore.dao;

import com.windowsxp.bookstore.entity.Order;
import com.windowsxp.bookstore.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderDao {
    Page<Order> getOrdersByUserId(Integer userId, Pageable pageable);
    Page<Order> getAllOrders(Pageable pageable);
    void saveOrder(Order order);
    Integer getOrderNumberByUserId(Integer userId);
    void addOrderItem(OrderItem orderItem);
}
