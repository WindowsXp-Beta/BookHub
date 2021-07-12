package com.windowsxp.bookstore.serviceimpl;

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
    public List<CartItem> getCart(Integer userId) {
          return cartDao.getCart(userId);
    }

    @Override
    public void addCart(Map<String, String> params) {
        CartItem newCartItem = new CartItem();
        Integer userId = Integer.valueOf(params.get("userId"));
        newCartItem.setUserId(userId);
        Integer bookId = Integer.valueOf(params.get("bookId"));
        Book newBook = new Book();
        newBook.setId(bookId);
        newCartItem.setBook(newBook);
        Integer bookNum = Integer.valueOf(params.get("bookNumber"));
        newCartItem.setBookNum(bookNum);
        cartDao.addCart(newCartItem);
    }

    @Override
    public void deleteCart(Map<String, String> params) {
        Integer itemId = Integer.valueOf(params.get("itemId"));
        cartDao.deleteCart(itemId);
    }

    @Override
    public void editCartItemNum(Map<String, String> params) {
        Integer itemId = Integer.valueOf(params.get("itemId"));
        Integer newBookNum = Integer.valueOf(params.get("bookNum"));
        cartDao.editCartItemNum(itemId, newBookNum);
    }
}