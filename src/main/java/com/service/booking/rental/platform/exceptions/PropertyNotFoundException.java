package com.service.booking.rental.platform.exceptions;

public class PropertyNotFoundException extends RuntimeException{

    public PropertyNotFoundException(Long id){
        super("Property not found for the specified id: " + id);
    }

}