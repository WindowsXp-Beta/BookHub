package com.windowsxp.bookstore.daoimpl;

import com.windowsxp.bookstore.dao.CartDao;
import com.windowsxp.bookstore.entity.CartItem;

import com.windowsxp.bookstore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CartDaoImpl implements CartDao {

    @Autowired
    CartRepository cartRepository;

    @Override
    public List<CartItem> getCart(Integer userId) {
        return cartRepository.getCart(userId);
    }

    @Override
    public void addCart(Map<String, String> params) {
        cartRepository.addCart(params);
    }

    @Override
    public void deleteCart(Map<String, String> params) {
        cartRepository.deleteCart(params);
    }
}
