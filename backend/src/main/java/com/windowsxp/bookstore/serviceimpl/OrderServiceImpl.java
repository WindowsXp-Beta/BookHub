package com.windowsxp.bookstore.serviceimpl;

import com.alibaba.fastjson.JSONObject;
import com.windowsxp.bookstore.dao.OrderDao;
import com.windowsxp.bookstore.entity.Order;
import com.windowsxp.bookstore.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> getAllOrders(Integer userId) {
        return orderDao.getAllOrders(userId);
    }

    @Override
    public void addOrder(JSONObject params) {
        orderDao.addOrder(params);
    }
}
