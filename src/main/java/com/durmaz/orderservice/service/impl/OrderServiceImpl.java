package com.durmaz.orderservice.service.impl;

import com.durmaz.orderservice.domain.Order;
import com.durmaz.orderservice.domain.OrderItem;
import com.durmaz.orderservice.domain.enums.OrderItemStatus;
import com.durmaz.orderservice.domain.enums.OrderStatus;
import com.durmaz.orderservice.repository.OrderItemRepository;
import com.durmaz.orderservice.repository.OrderRepository;
import com.durmaz.orderservice.service.OrderService;
import com.durmaz.orderservice.service.dto.CreateOrderRequestDTO;
import com.durmaz.orderservice.service.dto.OrderDTO;
import com.durmaz.orderservice.service.dto.OrderItemDTO;
import com.durmaz.orderservice.service.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemRepository orderItemRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper , OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderItemRepository = orderItemRepository;
    }


    @Override
    public OrderDTO creatOrder(CreateOrderRequestDTO orderRequestDTO) {
        String customerName = orderRequestDTO.getCustomerName();
        Integer totalQuantity = orderRequestDTO.getTotalQuantity();
        Double totalPrice = orderRequestDTO.getTotalAmount();
        String adress = orderRequestDTO.getAdress();
        LocalDateTime placedDate = LocalDateTime.now();
        OrderDTO orderDTO = new OrderDTO(null,customerName,totalQuantity,totalPrice, OrderStatus.PENDING,placedDate,adress);

        Order newOrder = orderRepository.save(orderMapper.toEntity(orderDTO));

        for(OrderItemDTO item: orderRequestDTO.getOrderItems()){
            OrderItem newItem = new OrderItem(
                    1L,
                    item.getQuantity(),
                    item.getTotalPrice(),
                    OrderItemStatus.PENDING,
                    item.getProductId(),
                    newOrder);
            orderItemRepository.save(newItem);
        }

        return orderMapper.toDto(newOrder);
    }
}
