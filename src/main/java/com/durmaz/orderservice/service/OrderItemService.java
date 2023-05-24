package com.durmaz.orderservice.service;

import com.durmaz.orderservice.service.dto.NewOrderItemDTO;
import com.durmaz.orderservice.service.dto.OrderItemDTO;

import java.util.List;

public interface OrderItemService {
    OrderItemDTO save(OrderItemDTO orderItemDTO);

    List<OrderItemDTO> getOrderItemByOrderId(Long orderId);
}
