package com.windowsxp.bookhub.backend.repository;

import com.windowsxp.bookhub.backend.entity.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    Page<CartItem> getCartItemByUserId(Integer userId, Pageable pageable);
    Optional<CartItem> findByUserIdAndBookId(Integer userId, Integer id);
    Integer countByUserId(Integer userId);
}