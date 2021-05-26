package com.windowsxp.bookstore.serviceimpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.windowsxp.bookstore.dao.OrderDao;
import com.windowsxp.bookstore.entity.Book;
import com.windowsxp.bookstore.entity.Order;
import com.windowsxp.bookstore.entity.OrderItem;
import com.windowsxp.bookstore.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> getOrders(Integer userId) {
        return orderDao.getOrders(userId);
    }

    @Override
    public void addOrder(JSONObject params) {
        Order newOrder = new Order();
        Timestamp orderTime = new Timestamp(System.currentTimeMillis());
        newOrder.setTime(orderTime);
        newOrder.setUserId(params.getInteger("userId"));
        orderDao.addOrder(newOrder);

        Integer orderId = newOrder.getOrderId();
        JSONArray jsonArray = params.getJSONArray("orderItems");
        for (int i = 0; i < jsonArray.size(); i++) {
            OrderItem newOrderItem = new OrderItem();
            JSONObject obj = jsonArray.getJSONObject(i);

            int bookId = Integer.parseInt(obj.get("bookId").toString());
            Book newBook = new Book();
            newBook.setId(bookId);

            int number = Integer.parseInt(obj.get("bookNum").toString());
            newOrderItem.setOrderId(orderId);
            newOrderItem.setBook(newBook);
            newOrderItem.setBookNum(number);
            orderDao.addOrderItem(newOrderItem);
        }
    }
}
