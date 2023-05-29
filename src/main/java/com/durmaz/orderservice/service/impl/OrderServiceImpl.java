package com.durmaz.orderservice.service.impl;

import com.durmaz.orderservice.domain.Order;
import com.durmaz.orderservice.domain.enums.OrderItemStatus;
import com.durmaz.orderservice.domain.enums.OrderStatus;
import com.durmaz.orderservice.repository.OrderRepository;
import com.durmaz.orderservice.service.OrderItemService;
import com.durmaz.orderservice.service.OrderService;
import com.durmaz.orderservice.service.dto.*;
import com.durmaz.orderservice.service.exception.OrderNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemService orderItemService;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
    }


    @Override
    public OrderDTO saveOrder(CreateOrderRequestDTO orderRequestDTO) {
        String customerName = orderRequestDTO.getCustomerName();
        Integer totalQuantity = orderRequestDTO.getTotalQuantity();
        Double totalPrice = orderRequestDTO.getTotalAmount();
        String adress = orderRequestDTO.getAdress();
        LocalDateTime placedDate = LocalDateTime.now();
        OrderDTO orderDTO = new OrderDTO(null,customerName,totalQuantity,totalPrice, OrderStatus.PENDING,placedDate,adress);

        Order newOrder = orderRepository.save(OrderDTO.toEntity(orderDTO));

        for(NewOrderItemDTO item: orderRequestDTO.getOrderItems()){
            OrderItemDTO orderItemDTO = new OrderItemDTO(
                    null,
                    item.getQuantity(),
                    item.getTotalPrice(),
                    OrderItemStatus.PENDING,
                    item.getProductId(),
                    OrderDTO.toDto(newOrder)

            );
            orderItemService.save(orderItemDTO);
        }

        return OrderDTO.toDto(newOrder);
    }

    @Override
    public ViewOrderDetailDTO getOrderDetailById(Long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order could not found by id " + id));
        OrderDTO orderDTO = OrderDTO.toDto(order);
        List<ViewOrderItemDTO> dtos = orderItemService.getOrderItemsDetailByOrderId(id);
        ViewOrderDetailDTO result = new ViewOrderDetailDTO(
                orderDTO.getId(),
                orderDTO.getCustomerName(),
                orderDTO.getTotalQuantitiy(),
                orderDTO.getTotalPrice(),
                orderDTO.getStatus(),
                orderDTO.getPlacedDate(),
                orderDTO.getAddress(),
                dtos
        );
        return result;
    }
}
