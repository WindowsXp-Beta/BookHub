package com.windowsxp.bookstore.controller;

import com.windowsxp.bookstore.constant.Constant;
import com.windowsxp.bookstore.dto.request.NewCartItemDTO;
import com.windowsxp.bookstore.entity.CartItem;
import com.windowsxp.bookstore.service.CartService;
import com.windowsxp.bookstore.utils.sessionutils.SessionUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/cart")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.USER)
    public ResponseEntity<Page<CartItem>> getCartByUserId(@RequestParam int page,
                                                          @RequestParam int pageSize) {
        try {
            return ResponseEntity.ok(cartService.getCartByUserId(SessionUtil.getAuth().getInteger(Constant.USER_ID), PageRequest.of(page, pageSize)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/cart")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.USER)
    public ResponseEntity<?> addCart(@RequestBody NewCartItemDTO newCartItemDTO) {
        try {
            cartService.addCart(SessionUtil.getAuth().getInteger(Constant.USER_ID), newCartItemDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/cart/{itemId}")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.USER)
    public ResponseEntity<?> deleteCart(@PathVariable Integer itemId) {
        try {
            cartService.deleteCart(itemId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/cart/{itemId}")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.USER)
    public ResponseEntity<?> editCartItemNumber(@PathVariable Integer itemId,
                                                @RequestParam Integer bookNumber) {
        try {
            cartService.editCartItemNumber(itemId, bookNumber);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
