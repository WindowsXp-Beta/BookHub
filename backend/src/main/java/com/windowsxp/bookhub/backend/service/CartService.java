package com.windowsxp.bookhub.backend.service;

import com.windowsxp.bookhub.backend.dto.request.NewCartItemDTO;
import com.windowsxp.bookhub.backend.dto.response.PageDTO;
import com.windowsxp.bookhub.backend.entity.CartItem;
import org.springframework.data.domain.Pageable;

public interface CartService {
    PageDTO<CartItem> getCartByUserId(Integer userId, Pageable pageable);
    void addCart(Integer userId, NewCartItemDTO newCartItemDTO);
    void deleteCart(Integer itemId);
    void editCartItemNumber(Integer itemId, Integer bookNumber);
    Integer getCartNumber(Integer userId);
}
