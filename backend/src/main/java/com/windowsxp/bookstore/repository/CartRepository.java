package com.windowsxp.bookstore.repository;

import com.windowsxp.bookstore.entity.CartItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<CartItem, Integer> {
}