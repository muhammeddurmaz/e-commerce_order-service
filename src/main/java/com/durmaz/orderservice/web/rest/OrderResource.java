package com.durmaz.orderservice.web.rest;

import com.durmaz.orderservice.service.OrderService;
import com.durmaz.orderservice.service.dto.CreateOrderRequestDTO;
import com.durmaz.orderservice.service.dto.OrderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderResource {

    private final OrderService orderService;

    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody CreateOrderRequestDTO requestDTO){
        OrderDTO orderDTO = orderService.creatOrder(requestDTO);
        return ResponseEntity.ok().body(orderDTO);
    }
}
