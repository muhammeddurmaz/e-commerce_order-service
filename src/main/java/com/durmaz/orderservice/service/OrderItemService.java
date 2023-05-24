package com.durmaz.orderservice.service;

import com.durmaz.orderservice.service.dto.NewOrderItemDTO;
import com.durmaz.orderservice.service.dto.OrderItemDTO;
import com.durmaz.orderservice.service.dto.ViewOrderItemDTO;

import java.util.List;

public interface OrderItemService {
    OrderItemDTO save(OrderItemDTO orderItemDTO);

    List<OrderItemDTO> getOrderItemByOrderId(Long orderId);

    ViewOrderItemDTO getOrderItemDetailsById(Long id);

    List<ViewOrderItemDTO> getOrderItemsDetailByOrderId(Long id);
}
