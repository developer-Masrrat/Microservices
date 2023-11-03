package com.nagarro.customer.services.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(){
        super("Resource not found on server !!!!!!!!");
    }
    public ResourceNotFoundException(String messgae){
        super(messgae);
    }
}
