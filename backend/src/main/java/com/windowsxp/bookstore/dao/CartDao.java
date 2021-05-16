package com.windowsxp.bookstore.dao;

import com.windowsxp.bookstore.entity.CartItem;

import java.util.List;
import java.util.Map;

public interface CartDao {
    List<CartItem> getCart(Integer usrtId);
    void addCart(Map<String, String> params);
    void deleteCart(Map<String, String> params);
}
