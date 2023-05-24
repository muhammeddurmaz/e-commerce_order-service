package com.durmaz.orderservice.service.exception;

public class BadRequestAlertException extends RuntimeException{

    public BadRequestAlertException(String message){
        super(message);
    }
}
