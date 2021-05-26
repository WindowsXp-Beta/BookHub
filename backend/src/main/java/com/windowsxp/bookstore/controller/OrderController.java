package com.windowsxp.bookstore.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.windowsxp.bookstore.constant.Constant;
import com.windowsxp.bookstore.entity.CartItem;
import com.windowsxp.bookstore.entity.Order;
import com.windowsxp.bookstore.service.BookService;
import com.windowsxp.bookstore.service.CartService;
import com.windowsxp.bookstore.service.OrderService;
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
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @RequestMapping("/getAllOrders")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public List<Order> getOrders(@RequestBody Map<String, String> params) {
        System.out.println("getAllOrders");
        Integer userId = Integer.valueOf(params.get(Constant.USER_ID));
        return userService.getOrders(userId);
    }
    @RequestMapping("/addOrder")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public Msg addOrder(@RequestBody Map<String, Object> params) {
        System.out.println("addOrder");
        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(params);
        orderService.addOrder(jsonObject);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.BUY_SUCCESS_MSG);
    }

    @RequestMapping("/getCart")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public List<CartItem> getCart(@RequestBody Map<String, String> params) {
        System.out.println("getCart");
        Integer userId = Integer.valueOf(params.get(Constant.USER_ID));
        return userService.getCart(userId);
    }

    @RequestMapping("/addCart")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public Msg addCart(@RequestBody Map<String, String> params) {
        System.out.println("addCart");
        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(params);
        cartService.addCart(jsonObject);
        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.ADD_SUCCESS_CART_MSG);
    }

    @RequestMapping("/deleteCart")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public Msg deleteCart(@RequestBody Map<String, String> params) {
        System.out.println("deleteCart");
        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(params);
        cartService.deleteCart(jsonObject);
        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.DELETE_SUCCESS_CART_MSG);
    }

    @RequestMapping("/editCartItemNum")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public Msg editCartItemNum(@RequestBody Map<String, String> params) {
        System.out.println("editCartNum");
        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(params);
        cartService.editCartItemNum(jsonObject);
        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.EDITNUM_SUCCESS_MSG);
    }
}