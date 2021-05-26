package com.windowsxp.bookstore.repository;

import com.windowsxp.bookstore.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}