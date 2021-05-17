package com.windowsxp.bookstore.daoimpl;

import com.alibaba.fastjson.JSONObject;
import com.windowsxp.bookstore.dao.OrderDao;
import com.windowsxp.bookstore.entity.Order;
import com.windowsxp.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public void addOrder(JSONObject params) {
        orderRepository.addOrder(params);
    }

//    @Override
//    public void addOrderItem(Map<String, String> params) {
//        orderRepository.addOrderItem(params);
//    }

    @Override
    public List<Order> getAllOrders(Integer userId) {
        return orderRepository.getAllOrders(userId);
    }
}
