package com.durmaz.orderservice.service.mapper;

import com.durmaz.orderservice.domain.Order;
import com.durmaz.orderservice.service.dto.ViewOrderDetailDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements OrderMapper{


    @Override
    public ViewOrderDetailDTO toDto(Order order) {
        ViewOrderDetailDTO result = new ViewOrderDetailDTO(
                order.getId(),
                order.getCustomerName(),
                order.getTotalQuantity(),
                order.getTotalPrice(),
                order.getStatus(),
                order.getPlacedDate(),
                order.getAddress()
        );
        return result;
    }
}
