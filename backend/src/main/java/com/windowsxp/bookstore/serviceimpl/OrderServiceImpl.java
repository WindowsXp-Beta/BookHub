package com.windowsxp.bookstore.serviceimpl;

import com.windowsxp.bookstore.dao.CartDao;
import com.windowsxp.bookstore.dao.OrderDao;
import com.windowsxp.bookstore.dto.request.NewOrderDTO;
import com.windowsxp.bookstore.entity.Book;
import com.windowsxp.bookstore.entity.Order;
import com.windowsxp.bookstore.entity.OrderItem;
import com.windowsxp.bookstore.service.OrderService;
import com.windowsxp.bookstore.utils.LogUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final CartDao cartDao;
    private final EntityManager entityManager;
    private final WebApplicationContext applicationContext;

    @Override
    public Page<Order> getOrders(Integer userId, Pageable pageable) {
        return orderDao.getOrdersByUserId(userId, pageable);
    }

    @Override
    public Integer getOrderNumber(Integer userId) {
        return orderDao.getOrderNumberByUserId(userId);
    }

    @Override
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderDao.getAllOrders(pageable);
    }

    @Override
    @JmsListener(destination = "OrderMessageBox", containerFactory = "myFactory")
    @Transactional(propagation = Propagation.REQUIRED)
    public void addOrder(NewOrderDTO newOrderDTO) {
        LogUtil.debug("begin address order" + newOrderDTO.toString());
        Order newOrder = new Order();
        Timestamp orderTime = new Timestamp(System.currentTimeMillis());
        newOrder.setTime(orderTime);
        newOrder.setUserId(newOrderDTO.getUserId());

        List<OrderItem> orderItemList = new LinkedList<>();
        List<Integer> cartItemIdList = new LinkedList<>();
        for (NewOrderDTO.OrderItem orderItem: newOrderDTO.getOrderItemList()) {
            OrderItem newOrderItem = new OrderItem();
            LogUtil.debug(newOrderDTO.toString());
            newOrderItem.setBook(entityManager.getReference(Book.class, orderItem.getBookId()));
            newOrderItem.setBookNumber(orderItem.getBookNumber());
            orderItemList.add(newOrderItem);
            cartItemIdList.add(orderItem.getCartItemId());
        }
        newOrder.setOrderItem(orderItemList);

        cartDao.deleteCartItems(cartItemIdList);
        orderDao.saveOrder(newOrder);
    }
}