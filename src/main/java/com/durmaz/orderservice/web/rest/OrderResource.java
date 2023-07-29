package com.durmaz.orderservice.web.rest;

import com.durmaz.orderservice.service.OrderService;
import com.durmaz.orderservice.service.dto.CreateOrderRequestDTO;
import com.durmaz.orderservice.service.dto.OrderDTO;
import com.durmaz.orderservice.service.dto.ResponseDTO;
import com.durmaz.orderservice.service.dto.ViewOrderDetailDTO;
import com.fasterxml.jackson.core.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderResource {

    Logger logger = LoggerFactory.getLogger(OrderResource.class);
    private static final String  ENTITY_NAME = "order";

    private final OrderService orderService;
    private final Environment environment;


    public OrderResource(OrderService orderService, Environment environment) {
        this.orderService = orderService;
        this.environment = environment;
    }

    @PostMapping("/orders")
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
        logger.info("Order Get on Port Number " + environment.getProperty("local.server.port"));
        ViewOrderDetailDTO result = orderService.getOrderDetailById(id);
        ResponseDTO responseDTO = new ResponseDTO<>()
                .message("Get Success", ENTITY_NAME)
                .success(true)
                .data(result);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/orders")
    public ResponseEntity<ResponseDTO> getAllOrderId(){
        List<Long> ids = orderService.getAllOrderId();
        ResponseDTO responseDTO = new ResponseDTO()
                .message("Get Success" , ENTITY_NAME)
                .success(true)
                .data(ids);
        return ResponseEntity.ok().body(responseDTO);
    }
}
