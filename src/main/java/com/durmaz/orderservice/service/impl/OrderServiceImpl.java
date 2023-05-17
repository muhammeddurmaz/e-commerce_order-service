package com.durmaz.orderservice.service.impl;

import com.durmaz.orderservice.repository.OrderRepository;
import com.durmaz.orderservice.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


}
