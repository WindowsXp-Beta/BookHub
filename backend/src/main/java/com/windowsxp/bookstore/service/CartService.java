package com.windowsxp.bookstore.service;

import com.windowsxp.bookstore.entity.CartItem;

import java.util.List;
import java.util.Map;

public interface CartService {
    List<CartItem> getCart(Integer userId);
    void addCart(Map<String, String> params);
    void deleteCart(Map<String, String> params);
    void editCartItemNum(Map<String, String> params);
}
