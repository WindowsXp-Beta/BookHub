package com.windowsxp.bookstore.repository;

import com.windowsxp.bookstore.entity.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
