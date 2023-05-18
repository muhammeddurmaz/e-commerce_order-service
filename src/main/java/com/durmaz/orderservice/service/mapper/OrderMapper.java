package com.durmaz.orderservice.service.mapper;

import com.durmaz.orderservice.domain.Order;
import com.durmaz.orderservice.service.dto.OrderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper extends EntityMapper<OrderDTO, Order> {
}
