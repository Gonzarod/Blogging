package com.acme.blogging.exception;

public class ResourceNotFoundException extends RuntimeException {
    ResourceNotFoundException(){
        super();
    }

    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(String message,Throwable cause) {
        super(message,cause);
    }
}
