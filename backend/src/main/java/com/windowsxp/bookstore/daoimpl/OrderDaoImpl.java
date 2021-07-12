package com.windowsxp.bookstore.daoimpl;

import com.alibaba.fastjson.JSONObject;
import com.windowsxp.bookstore.dao.OrderDao;
import com.windowsxp.bookstore.entity.Order;
import com.windowsxp.bookstore.entity.OrderItem;
import com.windowsxp.bookstore.repository.OrderItemRepository;
import com.windowsxp.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public List<Order> getOrders(Integer userId) {
        return orderRepository.getOrders(userId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void addOrder(Order order) {
        orderRepository.saveAndFlush(order);
    }

    @Override
    public void addOrderItem(OrderItem orderItem) {
        orderItemRepository.saveAndFlush(orderItem);
    }
}
