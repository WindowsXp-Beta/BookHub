package com.windowsxp.bookhub.backend.dao;

import com.windowsxp.bookhub.backend.entity.Order;
import com.windowsxp.bookhub.backend.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderDao {
    Page<Order> getOrdersByUserId(Integer userId, Pageable pageable);
    Page<Order> getAllOrders(Pageable pageable);
    void saveOrder(Order order);
    Integer getOrderNumberByUserId(Integer userId);
    void addOrderItem(OrderItem orderItem);
}
