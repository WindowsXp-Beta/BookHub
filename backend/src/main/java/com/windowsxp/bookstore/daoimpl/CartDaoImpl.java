package com.windowsxp.bookstore.daoimpl;

import com.windowsxp.bookstore.dao.CartDao;
import com.windowsxp.bookstore.entity.CartItem;

import com.windowsxp.bookstore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CartDaoImpl implements CartDao {

    @Autowired
    CartRepository cartRepository;

    @Override
    public List<CartItem> getCart(Integer userId) {
        return cartRepository.getCart(userId);
    }

    @Override
    public void addCart(CartItem cartItem) {
        cartRepository.saveAndFlush(cartItem);
    }

    @Override
    public void deleteCart(Integer itemId) {
        cartRepository.deleteById(itemId);
    }

    @Override
    public void editCartItemNum(Integer itemId, Integer bookNum) {
        CartItem cartItem = cartRepository.getById(itemId);
        cartItem.setBookNum(bookNum);
        cartRepository.saveAndFlush(cartItem);
    }
}
