package com.durmaz.orderservice.service.impl;

import com.durmaz.orderservice.domain.OrderItem;
import com.durmaz.orderservice.repository.OrderItemRepository;
import com.durmaz.orderservice.service.OrderItemService;
import com.durmaz.orderservice.service.dto.OrderItemDTO;
import com.durmaz.orderservice.service.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    private final OrderItemMapper orderItemMapper;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderItemMapper orderItemMapper) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemMapper = orderItemMapper;
    }

    @Override
    public OrderItemDTO save(OrderItemDTO orderItemDTO) {
        OrderItem newOrderItem = orderItemMapper.toEntity(orderItemDTO);
        OrderItem savedOrderItem = orderItemRepository.save(newOrderItem);
        OrderItemDTO result = orderItemMapper.toDto(savedOrderItem);
        return result;
    }

    @Override
    public List<OrderItemDTO> getOrderItemByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId).stream().map(orderItemMapper::toDto).collect(Collectors.toList());
    }


}
