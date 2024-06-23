package com.service.booking.rental.platform.exceptions;

public class InvalidDateException extends RuntimeException{
    public InvalidDateException(String message){
        super(message);
    }
}

