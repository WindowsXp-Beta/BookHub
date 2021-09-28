package com.windowsxp.bookstore.dao;

import com.windowsxp.bookstore.entity.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CartDao {
    CartItem getCartItemById(Integer itemId);
    Page<CartItem> getCartByUserId(Integer userId, Pageable pageable);
    Optional<CartItem> getCartByUserIdAndBookId(Integer userId, Integer bookId);
    void saveCart(CartItem cartItem);
    void deleteCart(Integer itemId);
    void deleteCartItems(List<Integer> itemList);
    Integer getCartNumberByUserId(Integer userId);
}
