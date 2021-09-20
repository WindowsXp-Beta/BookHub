package com.windowsxp.bookstore.daoimpl;

import com.windowsxp.bookstore.dao.CartDao;
import com.windowsxp.bookstore.entity.CartItem;
import com.windowsxp.bookstore.repository.CartItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Repository
@AllArgsConstructor
public class CartDaoImpl implements CartDao {

    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem getCartItemById(Integer itemId) {
        return cartItemRepository.getById(itemId);
    }

    @Override
    public Page<CartItem> getCartByUserId(Integer userId, Pageable pageable) {
        Page<CartItem> cart = cartItemRepository.getCartItemByUserId(userId, pageable);
        cart.getContent().removeIf(cartItem -> cartItem.getBook().getInventory() <= 0);
        return cart;
    }

    @Override
    public void saveCart(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteCart(Integer itemId) {
        cartItemRepository.deleteById(itemId);
    }
}
