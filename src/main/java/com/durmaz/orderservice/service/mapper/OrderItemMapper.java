package com.durmaz.orderservice.service.mapper;

import com.durmaz.orderservice.domain.Order;
import com.durmaz.orderservice.domain.OrderItem;
import com.durmaz.orderservice.service.dto.OrderDTO;
import com.durmaz.orderservice.service.dto.OrderItemDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface OrderItemMapper extends EntityMapper<OrderItemDTO, OrderItem>
{

    @Mapping(target = "order", source = "order", qualifiedByName = "orderId")
    OrderItemDTO toDto(OrderItem o);

    @Named("orderId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OrderDTO toDtoOrderId(Order order);
}
