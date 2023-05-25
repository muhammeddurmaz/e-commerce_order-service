package com.durmaz.orderservice.web.rest;

import com.durmaz.orderservice.service.OrderService;
import com.durmaz.orderservice.service.dto.CreateOrderRequestDTO;
import com.durmaz.orderservice.service.dto.OrderDTO;
import com.durmaz.orderservice.service.dto.ResponseDTO;
import com.durmaz.orderservice.service.dto.ViewOrderDetailDTO;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderResource {

    private static final String  ENTITY_NAME = "order";

    private final OrderService orderService;

    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<ResponseDTO> createOrder(@RequestBody CreateOrderRequestDTO requestDTO) throws JsonParseException {
        OrderDTO result = orderService.saveOrder(requestDTO);
        ResponseDTO responseDTO = new ResponseDTO<>()
                .message("Create Success",ENTITY_NAME)
                .success(true)
                .data(result);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<ResponseDTO> getViewOrder(@PathVariable(name ="id") Long id){
        ViewOrderDetailDTO result = orderService.getOrderDetailById(id);
        ResponseDTO responseDTO = new ResponseDTO<>()
                .message("Get Success", ENTITY_NAME)
                .success(true)
                .data(result);
        return ResponseEntity.ok().body(responseDTO);
    }
}
