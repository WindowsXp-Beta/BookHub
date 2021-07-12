package com.windowsxp.bookstore.controller;

import com.alibaba.fastjson.JSONObject;
import com.windowsxp.bookstore.constant.Constant;
import com.windowsxp.bookstore.entity.*;
import com.windowsxp.bookstore.service.CartService;
import com.windowsxp.bookstore.service.OrderService;

import com.windowsxp.bookstore.utils.msgutils.Msg;
import com.windowsxp.bookstore.utils.msgutils.MsgCode;
import com.windowsxp.bookstore.utils.msgutils.MsgUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;

    @RequestMapping("/getOrders")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public List<Order> getOrders(@RequestBody Map<String, String> params) {
        System.out.println("getOrders");
        Integer userId = Integer.valueOf(params.get(Constant.USER_ID));
        return orderService.getOrders(userId);
    }

    @RequestMapping("/getAllOrders")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public List<Order> getAllOrders() {
        System.out.println("getAllOrders");
        return orderService.getAllOrders();
    }

    @RequestMapping("/getOneBestSales")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public List<SaleStatistic> getOneBestSales(@RequestBody Map<String, String> params) {
        System.out.println("getOneBestSales");
        Integer userId = Integer.valueOf(params.get(Constant.USER_ID));
        List<Order> orderList = orderService.getOrders(userId);
        String timeStart = params.get("timeStart");
        if(timeStart != null) {
            timeStart = timeStart + " 00:00:00";
            String timeEnd = params.get("timeEnd");
            timeEnd = timeEnd + " 00:00:00";
            Timestamp timestampStart = Timestamp.valueOf(timeStart);
            Timestamp timestampEnd = Timestamp.valueOf(timeEnd);
            Iterator<Order> it = orderList.iterator();
            while(it.hasNext()) {
                Order order = it.next();
                if(order.getTime().after(timestampEnd) || order.getTime().before(timestampStart)) {
                    it.remove();
                }
            }
        }
        Map<Book, Integer> map = new HashMap<>();
        for(Order order: orderList) {
            for(OrderItem orderItem: order.getOrderItem()){
                Book book = orderItem.getBook();
                if(map.containsKey(book)){
                    Integer bookNumber = map.get(book);
                    bookNumber++;
                    map.put(book, bookNumber);
                }
                else {
                    map.put(book, 1);
                }
            }
        }
        List<SaleStatistic> SalesList = new ArrayList<>();
        for (Map.Entry<Book, Integer> entry : map.entrySet()) {
            SaleStatistic newEntry = new SaleStatistic();
            newEntry.setBook(entry.getKey());
            newEntry.setBookNumber(entry.getValue());
            newEntry.setSum(entry.getValue() * entry.getKey().getPrice());
            SalesList.add(newEntry);
        }
        return SalesList;
    }

    @RequestMapping("/getBestCustomer")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public List<CustomerStatistic> getBestCustomer(@RequestBody Map<String, String> params) {
        System.out.println("getBestCustomer");
        List<Order> orderList = orderService.getAllOrders();
        String timeStart = params.get("timeStart");
        if(timeStart != null) {
            timeStart = timeStart + " 00:00:00";
            String timeEnd = params.get("timeEnd");
            timeEnd = timeEnd + " 00:00:00";
            Timestamp timestampStart = Timestamp.valueOf(timeStart);
            Timestamp timestampEnd = Timestamp.valueOf(timeEnd);
            Iterator<Order> it = orderList.iterator();
            while(it.hasNext()) {
                Order order = it.next();
                if(order.getTime().after(timestampEnd) || order.getTime().before(timestampStart)) {
                    it.remove();
                }
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(Order order: orderList) {
            Integer userId = order.getUserId();
            for(OrderItem orderItem: order.getOrderItem()){
                Integer addNum = orderItem.getBookNum();
                Integer addSum = addNum * orderItem.getBook().getPrice();
                Integer preSum = map.get(userId);
                if(preSum != null){
                    preSum += addSum;
                }
                else {
                    preSum = addSum;
                }
                map.put(userId, preSum);
            }
        }
        List<CustomerStatistic> CustomerList = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            CustomerStatistic newEntry = new CustomerStatistic();
            newEntry.setUserId(entry.getKey());
            newEntry.setSum(entry.getValue());
            CustomerList.add(newEntry);
        }
        return CustomerList;
    }

    @RequestMapping("/getBestSales")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public List<SaleStatistic> getBestSales(@RequestBody Map<String, String> params) {
        System.out.println("getBestSales");
        List<Order> orderList = orderService.getAllOrders();
        String timeStart = params.get("timeStart");
        if(timeStart != null) {
            timeStart = timeStart + " 00:00:00";
            String timeEnd = params.get("timeEnd");
            timeEnd = timeEnd + " 00:00:00";
            Timestamp timestampStart = Timestamp.valueOf(timeStart);
            Timestamp timestampEnd = Timestamp.valueOf(timeEnd);
            Iterator<Order> it = orderList.iterator();
            while(it.hasNext()) {
                Order order = it.next();
                if(order.getTime().after(timestampEnd) || order.getTime().before(timestampStart)) {
                    it.remove();
                }
            }
        }
        Map<Book, Integer> map = new HashMap<>();
        for(Order order: orderList) {
            for(OrderItem orderItem: order.getOrderItem()){
                Book book = orderItem.getBook();
                if(map.containsKey(book)){
                    Integer bookNumber = map.get(book);
                    bookNumber++;
                    map.put(book, bookNumber);
                }
                else {
                    map.put(book, 1);
                }
            }
        }
        List<SaleStatistic> SalesList = new ArrayList<>();
        for (Map.Entry<Book, Integer> entry : map.entrySet()) {
            SaleStatistic newEntry = new SaleStatistic();
            newEntry.setBook(entry.getKey());
            newEntry.setBookNumber(entry.getValue());
            newEntry.setSum(entry.getValue() * entry.getKey().getPrice());
            SalesList.add(newEntry);
        }
        return SalesList;
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
        return cartService.getCart(userId);
    }

    @RequestMapping("/addCart")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public Msg addCart(@RequestBody Map<String, String> params) {
        System.out.println("addCart");
        cartService.addCart(params);
        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.ADD_SUCCESS_CART_MSG);
    }

    @RequestMapping("/deleteCart")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public Msg deleteCart(@RequestBody Map<String, String> params) {
        System.out.println("deleteCart");
        cartService.deleteCart(params);
        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.DELETE_SUCCESS_CART_MSG);
    }

    @RequestMapping("/editCartItemNum")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public Msg editCartItemNum(@RequestBody Map<String, String> params) {
        System.out.println("editCartNum");
        cartService.editCartItemNum(params);
        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.EDITNUM_SUCCESS_MSG);
    }
}