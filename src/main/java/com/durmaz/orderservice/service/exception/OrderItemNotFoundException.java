package com.durmaz.orderservice.service.exception;

public class OrderItemNotFoundException extends RuntimeException{


    public OrderItemNotFoundException(String message){
        super(message);
    }
}
