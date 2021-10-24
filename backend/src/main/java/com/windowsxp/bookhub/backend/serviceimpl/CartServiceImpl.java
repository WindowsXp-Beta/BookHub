package com.windowsxp.bookhub.backend.serviceimpl;

import com.windowsxp.bookhub.backend.dao.CartDao;
import com.windowsxp.bookhub.backend.dto.request.NewCartItemDTO;
import com.windowsxp.bookhub.backend.dto.response.PageDTO;
import com.windowsxp.bookhub.backend.entity.CartItem;
import com.windowsxp.bookhub.backend.service.CartService;
import com.windowsxp.bookhub.book.entity.Book;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartDao cartDao;
    private final EntityManager entityManager;

    @Override
    public PageDTO<CartItem> getCartByUserId(Integer userId, Pageable pageable) {
        Page<CartItem> cartItems = cartDao.getCartByUserId(userId, pageable);
        return new PageDTO<>(cartItems.getContent(), cartItems.getTotalElements());
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