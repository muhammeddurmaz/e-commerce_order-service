package com.durmaz.orderservice.web.rest;

import com.durmaz.orderservice.service.OrderService;
import com.durmaz.orderservice.service.dto.CreateOrderRequestDTO;
import com.durmaz.orderservice.service.dto.OrderDTO;
import com.durmaz.orderservice.service.dto.ViewOrderDetailDTO;
import com.fasterxml.jackson.core.JsonParseException;
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
    public ResponseEntity<OrderDTO> createOrder(@RequestBody CreateOrderRequestDTO requestDTO) throws JsonParseException {
        OrderDTO orderDTO = orderService.createOrder(requestDTO);
        return ResponseEntity.ok().body(orderDTO);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<ViewOrderDetailDTO> getViewOrder(@PathVariable(name ="id") Long id){
        return ResponseEntity.ok().body(orderService.getOrderDetailById(id));
    }
}
