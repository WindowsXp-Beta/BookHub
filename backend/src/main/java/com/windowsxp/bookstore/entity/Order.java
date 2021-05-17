package com.windowsxp.bookstore.entity;

import java.sql.Timestamp;
import java.util.List;

public class Order {
    private Integer orderId;
    private Integer userId;
    private Timestamp time;
    private OrderItem orderItem;

    public Order(Integer orderId, Integer userId, Timestamp time, OrderItem orderItem) {
        this.orderId = orderId;
        this.userId = userId;
        this.time = time;
        this.orderItem = orderItem;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Timestamp getTime() {
        return time;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
