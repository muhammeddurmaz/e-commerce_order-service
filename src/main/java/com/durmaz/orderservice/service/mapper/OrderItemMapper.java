package com.durmaz.orderservice.service.mapper;

import com.durmaz.orderservice.domain.OrderItem;
import com.durmaz.orderservice.service.dto.OrderItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper extends EntityMapper<OrderItemDTO, OrderItem>
{
}
