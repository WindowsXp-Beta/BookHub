package com.windowsxp.bookhub.backend.controller;

import com.windowsxp.bookhub.backend.constant.Constant;
import com.windowsxp.bookhub.backend.dto.request.EditCartItemDTO;
import com.windowsxp.bookhub.backend.dto.request.NewCartItemDTO;
import com.windowsxp.bookhub.backend.service.CartService;
import com.windowsxp.bookhub.backend.utils.LogUtil;
import com.windowsxp.bookhub.backend.utils.sessionutils.SessionUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@AllArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/cart")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.USER)
    public ResponseEntity<?> getCartByUserId(@RequestParam int page,
                                             @RequestParam int pageSize) {
        try {
            return ResponseEntity.ok(cartService.getCartByUserId(Objects.requireNonNull(SessionUtil.getAuth()).getInteger(Constant.USER_ID), PageRequest.of(page, pageSize)));
        } catch (RuntimeException e) {
            LogUtil.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/cart")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.USER)
    public ResponseEntity<?> addCart(@RequestBody NewCartItemDTO newCartItemDTO) {
        try {
            cartService.addCart(Objects.requireNonNull(SessionUtil.getAuth()).getInteger(Constant.USER_ID), newCartItemDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/cart/{itemId}")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.USER)
    public ResponseEntity<?> deleteCart(@PathVariable Integer itemId) {
        try {
            cartService.deleteCart(itemId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/cart/{itemId}")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.USER)
    public ResponseEntity<?> editCartItemNumber(@PathVariable Integer itemId,
                                                @RequestBody EditCartItemDTO editCartItemDTO) {
        try {
            cartService.editCartItemNumber(itemId, editCartItemDTO.getBookNumber());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
