package com.durmaz.orderservice.repository;

import com.durmaz.orderservice.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

    List<OrderItem> findAllByOrderId(Long orderId);
}
