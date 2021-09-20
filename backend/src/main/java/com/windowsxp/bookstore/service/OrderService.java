package com.windowsxp.bookstore.service;

import com.windowsxp.bookstore.dto.request.NewOrderDTO;
import com.windowsxp.bookstore.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderService {
    Page<Order> getOrders(Integer userId, Pageable pageable);
    Page<Order> getAllOrders(Pageable pageable);
    void addOrder(NewOrderDTO newOrderDTO);
}