package com.windowsxp.bookstore.service;

import com.alibaba.fastjson.JSONObject;


public interface OrderService {
    void addOrder(JSONObject params);
}