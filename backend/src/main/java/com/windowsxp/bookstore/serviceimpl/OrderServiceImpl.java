package com.windowsxp.bookstore.serviceimpl;

import com.windowsxp.bookstore.dao.OrderDao;
import com.windowsxp.bookstore.dto.request.NewOrderDTO;
import com.windowsxp.bookstore.entity.Book;
import com.windowsxp.bookstore.entity.Order;
import com.windowsxp.bookstore.entity.OrderItem;
import com.windowsxp.bookstore.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final EntityManager entityManager;

    @Override
    public Page<Order> getOrders(Integer userId, Pageable pageable) {
        return orderDao.getOrdersByUserId(userId, pageable);
    }

    @Override
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderDao.getAllOrders(pageable);
    }

    @Override
    public void addOrder(NewOrderDTO newOrderDTO) {
        Order newOrder = new Order();
        Timestamp orderTime = new Timestamp(System.currentTimeMillis());
        newOrder.setTime(orderTime);
        newOrder.setUserId(newOrderDTO.getUserId());

        List<OrderItem> orderItemList = new LinkedList<>();
        for (NewOrderDTO.OrderItem orderItem: newOrderDTO.getOrderItemList()) {
            OrderItem newOrderItem = new OrderItem();
            newOrderItem.setBook(entityManager.getReference(Book.class, orderItem.getBookId()));
            newOrderItem.setBookNumber(orderItem.getBookNumber());
            orderItemList.add(newOrderItem);
        }

        orderDao.saveOrder(newOrder);
    }
}