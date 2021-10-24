package com.windowsxp.bookhub.backend.daoimpl;

import com.windowsxp.bookhub.backend.dao.OrderDao;
import com.windowsxp.bookhub.backend.entity.Order;
import com.windowsxp.bookhub.backend.entity.OrderItem;
import com.windowsxp.bookhub.backend.repository.OrderItemRepository;
import com.windowsxp.bookhub.backend.repository.OrderRepository;
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
    public Integer getOrderNumberByUserId(Integer userId) {
        return orderRepository.countOrderByUserId(userId);
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
