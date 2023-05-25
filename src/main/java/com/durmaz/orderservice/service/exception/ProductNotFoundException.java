package com.durmaz.orderservice.service.exception;

public class ProductNotFoundException extends RuntimeException {


    private ExceptionMessage exceptionMessage;

    public ProductNotFoundException(String message){
        super(message);
    }

    public ProductNotFoundException(ExceptionMessage message){
        this.exceptionMessage = message;
    }
    public ProductNotFoundException(String message,ExceptionMessage exceptionMessage){
        super(message);
        this.exceptionMessage = exceptionMessage;
    }

    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }
}
