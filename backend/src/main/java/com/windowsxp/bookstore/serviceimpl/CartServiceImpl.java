package com.windowsxp.bookstore.serviceimpl;

import com.windowsxp.bookstore.dao.CartDao;
import com.windowsxp.bookstore.entity.CartItem;
import com.windowsxp.bookstore.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartDao cartDao;

    @Override
    public List<CartItem> getCart(Integer userId) {
        return cartDao.getCart(userId);
    }

    @Override
    public void addCart(Map<String, String> params) {
        cartDao.addCart(params);
    }

    @Override
    public void deleteCart(Map<String, String> params) {
        cartDao.deleteCart(params);
    }
}