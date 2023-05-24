package com.durmaz.orderservice.service;

import com.durmaz.orderservice.service.dto.CreateOrderRequestDTO;
import com.durmaz.orderservice.service.dto.OrderDTO;

public interface OrderService {
    OrderDTO createOrder(CreateOrderRequestDTO orderRequestDTO);
}
