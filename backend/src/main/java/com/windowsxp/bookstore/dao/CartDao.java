package com.windowsxp.bookstore.dao;

import com.windowsxp.bookstore.entity.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartDao {
    CartItem getCartItemById(Integer itemId);
    Page<CartItem> getCartByUserId(Integer userId, Pageable pageable);
    void saveCart(CartItem cartItem);
    void deleteCart(Integer itemId);
}
