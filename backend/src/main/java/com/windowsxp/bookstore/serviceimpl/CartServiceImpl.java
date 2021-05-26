package com.windowsxp.bookstore.serviceimpl;

import com.alibaba.fastjson.JSONObject;
import com.windowsxp.bookstore.dao.CartDao;
import com.windowsxp.bookstore.entity.Book;
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
    public void addCart(JSONObject params) {
        CartItem newCartItem = new CartItem();
        Integer userId = params.getInteger("userId");
        newCartItem.setUserId(userId);
        Integer bookId = params.getInteger("bookId");
        Book newBook = new Book();
        newBook.setId(bookId);
        newCartItem.setBook(newBook);
        Integer bookNum = params.getInteger("bookNum");
        newCartItem.setBookNum(bookNum);
        cartDao.addCart(newCartItem);
    }

    @Override
    public void deleteCart(JSONObject params) {
        Integer itemId = params.getInteger("itemId");
        cartDao.deleteCart(itemId);
    }

    @Override
    public void editCartItemNum(JSONObject params) {
        Integer itemId = params.getInteger("itemId");
        Integer newBookNum = params.getInteger("bookNum");
        cartDao.editCartItemNum(itemId, newBookNum);
    }
}