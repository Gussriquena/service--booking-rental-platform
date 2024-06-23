package com.service.booking.rental.platform.exceptions;

public class PropertyUnavailableException extends RuntimeException{
    public PropertyUnavailableException(String message){
        super(message);
    }
}