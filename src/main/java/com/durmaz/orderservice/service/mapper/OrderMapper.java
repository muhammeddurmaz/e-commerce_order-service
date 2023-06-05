package com.durmaz.orderservice.service.mapper;

import com.durmaz.orderservice.domain.Order;
import com.durmaz.orderservice.service.dto.ViewOrderDetailDTO;

public interface OrderMapper {
    ViewOrderDetailDTO toDto(Order order);
}
