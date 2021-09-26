package com.windowsxp.bookstore.repository;

import com.windowsxp.bookstore.entity.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    Page<CartItem> getCartItemByUserId(Integer userId, Pageable pageable);
}