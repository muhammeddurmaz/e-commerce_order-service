package com.durmaz.orderservice.service.impl;

import com.durmaz.orderservice.client.ProductServiceClient;
import com.durmaz.orderservice.domain.Order;
import com.durmaz.orderservice.domain.enums.OrderItemStatus;
import com.durmaz.orderservice.domain.enums.OrderStatus;
import com.durmaz.orderservice.repository.OrderRepository;
import com.durmaz.orderservice.service.OrderItemService;
import com.durmaz.orderservice.service.OrderService;
import com.durmaz.orderservice.service.dto.*;
import com.durmaz.orderservice.service.exception.OrderNotFoundException;
import com.durmaz.orderservice.service.exception.ProductNotFoundException;
import com.durmaz.orderservice.service.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemService orderItemService;

    private final OrderMapper orderMapper;

    private final ProductServiceClient productServiceClient;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemService orderItemService, OrderMapper orderMapper, ProductServiceClient productServiceClient) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
        this.orderMapper = orderMapper;
        this.productServiceClient = productServiceClient;
    }


    @Override
    public OrderDTO saveOrder(CreateOrderRequestDTO orderRequestDTO) {
        List<Long> productIds = orderRequestDTO.getOrderItems().stream().map(o-> o.getProductId()).collect(Collectors.toList());
        Double price = productServiceClient.getSumProductPrice(productIds).getBody().getData();
        if (price == null){
            throw new ProductNotFoundException("Product Not Found");
        }
        Double totalPrice;
        if(price.equals(orderRequestDTO.getTotalAmount())){
            totalPrice = orderRequestDTO.getTotalAmount();
        }else {
            totalPrice = price;
        }

        Integer quantity = orderRequestDTO.getOrderItems().stream().mapToInt(NewOrderItemDTO::getQuantity).sum();
        Integer totalQuantity;
        if (quantity.equals(orderRequestDTO.getTotalQuantity())){
            totalQuantity = orderRequestDTO.getTotalQuantity();
        }else {
            totalQuantity = quantity;
        }

        String customerName = orderRequestDTO.getCustomerName();
        String adress = orderRequestDTO.getAdress();
        LocalDateTime placedDate = LocalDateTime.now();
        OrderDTO orderDTO = new OrderDTO(customerName,totalQuantity,totalPrice, OrderStatus.PENDING,placedDate,adress);

        Order newOrder = orderRepository.save(OrderDTO.toEntity(orderDTO));

        for(NewOrderItemDTO item: orderRequestDTO.getOrderItems()){
            OrderItemDTO orderItemDTO = new OrderItemDTO(
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
        ViewOrderDetailDTO result = orderMapper.toDto(order);
        result.setOrderItemDetails(dtos);
        return result;
    }

    @Override
    public List<Long> getAllOrderId(){
        List<Long> ids = orderRepository.findAll()
                .stream().map(o-> o.getId())
                .collect(Collectors.toList());
        return ids;
    }
}
