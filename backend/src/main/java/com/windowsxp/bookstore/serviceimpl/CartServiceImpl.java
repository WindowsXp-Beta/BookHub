package com.windowsxp.bookstore.serviceimpl;

import com.windowsxp.bookstore.dao.CartDao;
import com.windowsxp.bookstore.dto.request.NewCartItemDTO;
import com.windowsxp.bookstore.dto.response.PageDTO;
import com.windowsxp.bookstore.entity.Book;
import com.windowsxp.bookstore.entity.CartItem;
import com.windowsxp.bookstore.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartDao cartDao;
    private final EntityManager entityManager;

    @Override
    public PageDTO<CartItem> getCartByUserId(Integer userId, Pageable pageable) {
          return new PageDTO<>(cartDao.getCartByUserId(userId, pageable));
    }

    @Override
    public void addCart(Integer userId, NewCartItemDTO newCartItemDTO) {
        Integer bookId = newCartItemDTO.getBookId();
        Optional<CartItem> optionalCartItem = cartDao.getCartByUserIdAndBookId(userId, bookId);
        if(optionalCartItem.isEmpty()){
            CartItem newCartItem = new CartItem();
            newCartItem.setUserId(userId);
            newCartItem.setBook(entityManager.getReference(Book.class, newCartItemDTO.getBookId()));
            newCartItem.setBookNumber(newCartItemDTO.getBookNumber());
            cartDao.saveCart(newCartItem);
        } else {
            CartItem cartItem = optionalCartItem.get();
            cartItem.setBookNumber(cartItem.getBookNumber() + newCartItemDTO.getBookNumber());
            cartDao.saveCart(cartItem);
        }
    }

    @Override
    public Integer getCartNumber(Integer userId){
        return cartDao.getCartNumberByUserId(userId);
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