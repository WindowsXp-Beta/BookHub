package com.windowsxp.bookstore.repository;

import com.windowsxp.bookstore.entity.CartItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItem, Integer> {

    @Query(value = "from CartItem where userId=:userId")
    List<CartItem> getCart(@Param("userId") Integer userId);
}