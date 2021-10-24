package com.windowsxp.bookhub.backend.daoimpl;

import com.windowsxp.bookhub.backend.dao.CartDao;
import com.windowsxp.bookhub.backend.entity.CartItem;
import com.windowsxp.bookhub.backend.repository.CartItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


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
        return cartItemRepository.getCartItemByUserId(userId, pageable);
    }

    @Override
    public Optional<CartItem> getCartByUserIdAndBookId(Integer userId, Integer bookId){
        return cartItemRepository.findByUserIdAndBookId(userId, bookId);
    }

    @Override
    public void saveCart(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteCart(Integer itemId) {
        cartItemRepository.deleteById(itemId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCartItems(List<Integer> itemList) {
        cartItemRepository.deleteAllByIdInBatch(itemList);
    }

    @Override
    public Integer getCartNumberByUserId(Integer userId) {
        return cartItemRepository.countByUserId(userId);
    }
}
