package com.durmaz.orderservice.web.rest;

import com.durmaz.orderservice.service.OrderItemService;
import com.durmaz.orderservice.service.dto.ViewOrderItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderItemResource {

    private final OrderItemService orderItemService;

    public OrderItemResource(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/orderitem/{id}")
    public ResponseEntity<ViewOrderItemDTO> getOrderItemDetailsById(@PathVariable(name = "id")Long id){
        ViewOrderItemDTO result = orderItemService.getOrderItemDetailsById(id);
        return ResponseEntity.ok().body(result);
    }
}
