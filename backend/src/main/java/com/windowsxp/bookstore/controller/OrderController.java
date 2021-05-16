package com.windowsxp.bookstore.controller;

import com.alibaba.fastjson.JSONObject;
import com.windowsxp.bookstore.constant.Constant;
import com.windowsxp.bookstore.entity.CartItem;
import com.windowsxp.bookstore.service.BookService;
import com.windowsxp.bookstore.service.CartService;
import com.windowsxp.bookstore.service.UserService;

import com.windowsxp.bookstore.utils.msgutils.Msg;
import com.windowsxp.bookstore.utils.msgutils.MsgCode;
import com.windowsxp.bookstore.utils.msgutils.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private BookService bookService;
//    @Autowired
//    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

//    @RequestMapping("/getOrders")
//    public List<Order> getOrders(@RequestBody Map<String, String> params) {
//        System.out.println("getOrders");
//        Integer userId = Integer.valueOf(params.get(Constant.USER_ID));
//        return orderService.getOrders(userId);
//    }

    @RequestMapping("/getCart")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public List<CartItem> getCart(@RequestBody Map<String, String> params) {
        System.out.println("getCart");
        Integer userId = Integer.valueOf(params.get(Constant.USER_ID));
        return cartService.getCart(userId);
    }

    @RequestMapping("/addCart")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public Msg addCart(@RequestBody Map<String, String> params) {
        System.out.println("addCart");
        cartService.addCart(params);
        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.ADD_SUCCESS_MSG);
    }

    @RequestMapping("/deleteCart")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public Msg deleteCart(@RequestBody Map<String, String> params) {
        System.out.println("deleteCart");
        cartService.deleteCart(params);
        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.DELETE_SUCCESS_MSG);
    }

    @RequestMapping("/editCartItemNum")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public Msg editCartItemNum(@RequestBody Map<String, String> params) {
        System.out.println("editCartNum");
        cartService.editCartItemNum(params);
        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.EDITNUM_SUCCESS_MSG);
    }
}