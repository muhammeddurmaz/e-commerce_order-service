package com.durmaz.orderservice.service.impl;

import com.durmaz.orderservice.client.ProductServiceClient;
import com.durmaz.orderservice.domain.OrderItem;
import com.durmaz.orderservice.repository.OrderItemRepository;
import com.durmaz.orderservice.service.OrderItemService;
import com.durmaz.orderservice.service.dto.OrderItemDTO;
import com.durmaz.orderservice.service.dto.ProductDTO;
import com.durmaz.orderservice.service.dto.ViewOrderItemDTO;
import com.durmaz.orderservice.service.exception.BadRequestAlertException;
import com.durmaz.orderservice.service.exception.OrderItemNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;


    private final ProductServiceClient productServiceClient;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, ProductServiceClient productServiceClient) {
        this.orderItemRepository = orderItemRepository;
        this.productServiceClient = productServiceClient;
    }

    @Override
    public OrderItemDTO save(OrderItemDTO orderItemDTO) {
        OrderItem savedOrderItem = orderItemRepository.save(OrderItemDTO.toEntity(orderItemDTO));
        return OrderItemDTO.toDto(savedOrderItem);
    }

    @Override
    public List<OrderItemDTO> getOrderItemByOrderId(Long orderId) {
        return orderItemRepository.findAllByOrderId(orderId).stream().map(OrderItemDTO::toDto).collect(Collectors.toList());
    }

    @Override
    public ViewOrderItemDTO getOrderItemDetailsById(Long id){
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new OrderItemNotFoundException( "OrderItem could not found by id " + id));

        ProductDTO productDTO = productServiceClient.getProductById(orderItem.getProductId()).getBody().getData();
        ViewOrderItemDTO dto = new ViewOrderItemDTO(
                orderItem.getId(),
                orderItem.getQuantity(),
                orderItem.getTotalPrice(),
                orderItem.getStatus(),
                productDTO
        );
        return dto;
    }

    @Override
    public List<ViewOrderItemDTO> getOrderItemsDetailByOrderId(Long id){
        List<OrderItemDTO> orderItems = orderItemRepository.findAllByOrderId(id).stream().map(OrderItemDTO::toDto).collect(Collectors.toList());
        List<ViewOrderItemDTO> resultDtos = new ArrayList<>();
        for(OrderItemDTO orderItem : orderItems){
            ProductDTO productDTO = productServiceClient
                    .getProductById(orderItem.getProductId())
                    .getBody()
                    .getData();

            if(productDTO == null){
                throw new BadRequestAlertException("Product Not Fount");
            }
            ViewOrderItemDTO dto = new ViewOrderItemDTO(
                    orderItem.getId(),
                    orderItem.getQuantity(),
                    orderItem.getTotalPrice(),
                    orderItem.getStatus(),
                    productDTO
            );
            resultDtos.add(dto);
        }
        return resultDtos;
    }

}
