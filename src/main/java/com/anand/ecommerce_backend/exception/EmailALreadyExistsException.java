package com.anand.ecommerce_backend.exception;

public class EmailALreadyExistsException extends RuntimeException{
    public EmailALreadyExistsException(String message){
        super(message);
    }
}
