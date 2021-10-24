package com.windowsxp.bookhub.backend.repository;

import com.windowsxp.bookhub.backend.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
