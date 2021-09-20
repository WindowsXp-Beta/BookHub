package com.windowsxp.bookstore.serviceimpl;

import com.windowsxp.bookstore.dao.CartDao;
import com.windowsxp.bookstore.dto.request.NewCartItemDTO;
import com.windowsxp.bookstore.entity.Book;
import com.windowsxp.bookstore.entity.CartItem;
import com.windowsxp.bookstore.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartDao cartDao;
    private final EntityManager entityManager;

    @Override
    public Page<CartItem> getCartByUserId(Integer userId, Pageable pageable) {
          return cartDao.getCartByUserId(userId, pageable);
    }

    @Override
    public void addCart(Integer userId, NewCartItemDTO newCartItemDTO) {
        CartItem newCartItem = new CartItem();
        newCartItem.setUserId(userId);
        newCartItem.setBook(entityManager.getReference(Book.class, newCartItemDTO.getBookId()));
        newCartItem.setBookNumber(newCartItemDTO.getBookNumber());
        cartDao.saveCart(newCartItem);
    }

    @Override
    public void deleteCart(Integer itemId) {
        cartDao.deleteCart(itemId);
    }

    @Override
    public void editCartItemNumber(Integer itemId, Integer bookNumber) {
        CartItem cartItem = cartDao.getCartItemById(itemId);
        cartItem.setBookNumber(bookNumber);
        cartDao.saveCart(cartItem);
    }
}