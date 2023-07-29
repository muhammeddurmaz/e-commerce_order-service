package com.durmaz.orderservice.service;

import com.durmaz.orderservice.service.dto.CreateOrderRequestDTO;
import com.durmaz.orderservice.service.dto.OrderDTO;
import com.durmaz.orderservice.service.dto.ViewOrderDetailDTO;

import java.util.List;

public interface OrderService {
    OrderDTO saveOrder(CreateOrderRequestDTO orderRequestDTO);

    ViewOrderDetailDTO getOrderDetailById(Long id);

    List<Long> getAllOrderId();
}
