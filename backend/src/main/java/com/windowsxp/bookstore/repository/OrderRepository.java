package com.windowsxp.bookstore.repository;

import com.windowsxp.bookstore.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "from Order where userId=:userId")
    List<Order> getOrders(@Param("userId") Integer userId);
}