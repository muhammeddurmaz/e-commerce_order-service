package com.durmaz.orderservice.service;

import com.durmaz.orderservice.service.dto.CreateOrderRequestDTO;
import com.durmaz.orderservice.service.dto.OrderDTO;
import com.durmaz.orderservice.service.dto.ViewOrderDetailDTO;

public interface OrderService {
    OrderDTO createOrder(CreateOrderRequestDTO orderRequestDTO);

    ViewOrderDetailDTO getOrderDetailById(Long id);
}
