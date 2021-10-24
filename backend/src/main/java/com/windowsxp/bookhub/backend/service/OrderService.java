package com.windowsxp.bookhub.backend.service;

import com.windowsxp.bookhub.backend.dto.request.NewOrderDTO;
import com.windowsxp.bookhub.backend.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderService {
    Page<Order> getOrders(Integer userId, Pageable pageable);
    Page<Order> getAllOrders(Pageable pageable);
    Integer getOrderNumber(Integer userId);
    void addOrder(NewOrderDTO newOrderDTO);
}