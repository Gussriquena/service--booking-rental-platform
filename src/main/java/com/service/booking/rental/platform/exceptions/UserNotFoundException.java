package com.service.booking.rental.platform.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("User not found for the specified id: " + id);
    }
}
