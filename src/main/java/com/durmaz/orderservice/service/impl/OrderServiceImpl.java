package com.durmaz.orderservice.service.impl;

import com.durmaz.orderservice.domain.Order;
import com.durmaz.orderservice.domain.enums.OrderStatus;
import com.durmaz.orderservice.repository.OrderRepository;
import com.durmaz.orderservice.service.OrderItemService;
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

    private final OrderItemService orderItemService;

    public OrderServiceImpl(OrderRepository orderRepository,OrderMapper orderMapper, OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderItemService = orderItemService;
    }


    @Override
    public OrderDTO createOrder(CreateOrderRequestDTO orderRequestDTO) {
        String customerName = orderRequestDTO.getCustomerName();
        Integer totalQuantity = orderRequestDTO.getTotalQuantity();
        Double totalPrice = orderRequestDTO.getTotalAmount();
        String adress = orderRequestDTO.getAdress();
        LocalDateTime placedDate = LocalDateTime.now();
        OrderDTO orderDTO = new OrderDTO(null,customerName,totalQuantity,totalPrice, OrderStatus.PENDING,placedDate,adress);

        Order newOrder = orderRepository.save(orderMapper.toEntity(orderDTO));

        for(OrderItemDTO item: orderRequestDTO.getOrderItems()){
            item.setOrderID(newOrder.getId());
            orderItemService.save(item);
        }

        return orderMapper.toDto(newOrder);
    }
}
