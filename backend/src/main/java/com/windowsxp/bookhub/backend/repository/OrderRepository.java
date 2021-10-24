package com.windowsxp.bookhub.backend.repository;

import com.windowsxp.bookhub.backend.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Page<Order> findAll(Pageable pageable);
    Page<Order> getOrdersByUserId(Integer userId, Pageable pageable);
    Integer countOrderByUserId(Integer userId);
}