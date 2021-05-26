package com.windowsxp.bookstore.service;


import com.alibaba.fastjson.JSONObject;
import com.windowsxp.bookstore.entity.CartItem;

import java.util.List;

public interface CartService {
    List<CartItem> getCart(Integer userId);
    void addCart(JSONObject params);
    void deleteCart(JSONObject params);
    void editCartItemNum(JSONObject params);
}
