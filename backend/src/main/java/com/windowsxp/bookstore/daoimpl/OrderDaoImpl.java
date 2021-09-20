package com.windowsxp.bookstore.daoimpl;

import com.windowsxp.bookstore.dao.OrderDao;
import com.windowsxp.bookstore.entity.Order;
import com.windowsxp.bookstore.entity.OrderItem;
import com.windowsxp.bookstore.repository.OrderItemRepository;
import com.windowsxp.bookstore.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class OrderDaoImpl implements OrderDao {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public Page<Order> getOrdersByUserId(Integer userId, Pageable pageable) {
        return orderRepository.getOrdersByUserId(userId, pageable);
    }

    @Override
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void addOrderItem(OrderItem orderItem) {
        orderItemRepository.saveAndFlush(orderItem);
    }
}
