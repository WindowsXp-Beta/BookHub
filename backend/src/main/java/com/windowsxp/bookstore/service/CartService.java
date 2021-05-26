package com.windowsxp.bookstore.service;


import com.alibaba.fastjson.JSONObject;

public interface CartService {
    void addCart(JSONObject params);
    void deleteCart(JSONObject params);
    void editCartItemNum(JSONObject params);
}
