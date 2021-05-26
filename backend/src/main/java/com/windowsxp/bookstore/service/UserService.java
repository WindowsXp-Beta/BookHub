package com.windowsxp.bookstore.service;


import com.windowsxp.bookstore.entity.CartItem;
import com.windowsxp.bookstore.entity.Order;
import com.windowsxp.bookstore.entity.User;

import java.util.List;

public interface UserService {

    User checkUser(String username, String password);
    User getUser(Integer id);
    List<Order> getOrders(Integer id);
    List<CartItem> getCart(Integer id);
}
