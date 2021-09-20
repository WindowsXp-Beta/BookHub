package com.windowsxp.bookstore.controller;

import com.windowsxp.bookstore.constant.Constant;
import com.windowsxp.bookstore.dto.request.NewOrderDTO;
import com.windowsxp.bookstore.entity.Order;
import com.windowsxp.bookstore.service.OrderService;
import com.windowsxp.bookstore.utils.sessionutils.SessionUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/order")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.USER)
    public ResponseEntity<Page<Order>> getOrdersByUser(@RequestParam int page,
                                                       @RequestParam int pageSize) {
        try {
            return ResponseEntity.ok(orderService.getOrders(SessionUtil.getAuth().getInteger(Constant.USER_ID), PageRequest.of(page, pageSize)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/admin/order")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.ADMIN)
    public ResponseEntity<Page<Order>> getAllOrders(@RequestParam int page,
                                                    @RequestParam int pageSize) {
        try {
            return ResponseEntity.ok(orderService.getAllOrders(PageRequest.of(page, pageSize)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/order")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.USER)
    public ResponseEntity<?> addOrder(@RequestBody NewOrderDTO newOrderDTO) {
        try {
            orderService.addOrder(newOrderDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    @RequestMapping("/getOneBestSales")
//    public List<SaleStatistic> getOneBestSales(@RequestBody Map<String, String> params) {
//        Integer userId = Integer.valueOf(params.get(Constant.USER_ID));
//        List<Order> orderList = orderService.getOrders(userId, );
//        String timeStart = params.get("timeStart");
//        if(timeStart != null) {
//            timeStart = timeStart + " 00:00:00";
//            String timeEnd = params.get("timeEnd");
//            timeEnd = timeEnd + " 00:00:00";
//            Timestamp timestampStart = Timestamp.valueOf(timeStart);
//            Timestamp timestampEnd = Timestamp.valueOf(timeEnd);
//            Iterator<Order> it = orderList.iterator();
//            while(it.hasNext()) {
//                Order order = it.next();
//                if(order.getTime().after(timestampEnd) || order.getTime().before(timestampStart)) {
//                    it.remove();
//                }
//            }
//        }
//        Map<Book, Integer> map = new HashMap<>();
//        for(Order order: orderList) {
//            for(OrderItem orderItem: order.getOrderItem()){
//                Book book = orderItem.getBook();
//                if(map.containsKey(book)){
//                    Integer bookNumber = map.get(book);
//                    bookNumber++;
//                    map.put(book, bookNumber);
//                }
//                else {
//                    map.put(book, 1);
//                }
//            }
//        }
//        List<SaleStatistic> SalesList = new ArrayList<>();
//        for (Map.Entry<Book, Integer> entry : map.entrySet()) {
//            SaleStatistic newEntry = new SaleStatistic();
//            newEntry.setBook(entry.getKey());
//            newEntry.setBookNumber(entry.getValue());
//            newEntry.setSum(entry.getValue() * entry.getKey().getPrice());
//            SalesList.add(newEntry);
//        }
//        return SalesList;
//    }
//
//    @RequestMapping("/getBestCustomer")
//    public List<CustomerStatistic> getBestCustomer(@RequestBody Map<String, String> params) {
//        System.out.println("getBestCustomer");
//        List<Order> orderList = orderService.getAllOrders();
//        String timeStart = params.get("timeStart");
//        if(timeStart != null) {
//            timeStart = timeStart + " 00:00:00";
//            String timeEnd = params.get("timeEnd");
//            timeEnd = timeEnd + " 00:00:00";
//            Timestamp timestampStart = Timestamp.valueOf(timeStart);
//            Timestamp timestampEnd = Timestamp.valueOf(timeEnd);
//            Iterator<Order> it = orderList.iterator();
//            while(it.hasNext()) {
//                Order order = it.next();
//                if(order.getTime().after(timestampEnd) || order.getTime().before(timestampStart)) {
//                    it.remove();
//                }
//            }
//        }
//        Map<Integer, Integer> map = new HashMap<>();
//        for(Order order: orderList) {
//            Integer userId = order.getUserId();
//            for(OrderItem orderItem: order.getOrderItem()){
//                Integer addNum = orderItem.getBookNum();
//                Integer addSum = addNum * orderItem.getBook().getPrice();
//                Integer preSum = map.get(userId);
//                if(preSum != null){
//                    preSum += addSum;
//                }
//                else {
//                    preSum = addSum;
//                }
//                map.put(userId, preSum);
//            }
//        }
//        List<CustomerStatistic> CustomerList = new ArrayList<>();
//        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
//            CustomerStatistic newEntry = new CustomerStatistic();
//            newEntry.setUserId(entry.getKey());
//            newEntry.setSum(entry.getValue());
//            CustomerList.add(newEntry);
//        }
//        return CustomerList;
//    }
//
//    @RequestMapping("/getBestSales")
//    public List<SaleStatistic> getBestSales(@RequestBody Map<String, String> params) {
//        System.out.println("getBestSales");
//        List<Order> orderList = orderService.getAllOrders();
//        String timeStart = params.get("timeStart");
//        if(timeStart != null) {
//            timeStart = timeStart + " 00:00:00";
//            String timeEnd = params.get("timeEnd");
//            timeEnd = timeEnd + " 00:00:00";
//            Timestamp timestampStart = Timestamp.valueOf(timeStart);
//            Timestamp timestampEnd = Timestamp.valueOf(timeEnd);
//            Iterator<Order> it = orderList.iterator();
//            while(it.hasNext()) {
//                Order order = it.next();
//                if(order.getTime().after(timestampEnd) || order.getTime().before(timestampStart)) {
//                    it.remove();
//                }
//            }
//        }
//        Map<Book, Integer> map = new HashMap<>();
//        for(Order order: orderList) {
//            for(OrderItem orderItem: order.getOrderItem()){
//                Book book = orderItem.getBook();
//                if(map.containsKey(book)){
//                    Integer bookNumber = map.get(book);
//                    bookNumber++;
//                    map.put(book, bookNumber);
//                }
//                else {
//                    map.put(book, 1);
//                }
//            }
//        }
//        List<SaleStatistic> SalesList = new ArrayList<>();
//        for (Map.Entry<Book, Integer> entry : map.entrySet()) {
//            SaleStatistic newEntry = new SaleStatistic();
//            newEntry.setBook(entry.getKey());
//            newEntry.setBookNumber(entry.getValue());
//            newEntry.setSum(entry.getValue() * entry.getKey().getPrice());
//            SalesList.add(newEntry);
//        }
//        return SalesList;
//    }
}