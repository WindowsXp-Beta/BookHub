package com.windowsxp.bookstore.service;

import com.windowsxp.bookstore.dto.request.NewCartItemDTO;
import com.windowsxp.bookstore.dto.response.PageDTO;
import com.windowsxp.bookstore.entity.CartItem;
import org.springframework.data.domain.Pageable;

public interface CartService {
    PageDTO<CartItem> getCartByUserId(Integer userId, Pageable pageable);
    void addCart(Integer userId, NewCartItemDTO newCartItemDTO);
    void deleteCart(Integer itemId);
    void editCartItemNumber(Integer itemId, Integer bookNumber);
    Integer getCartNumber(Integer userId);
}
