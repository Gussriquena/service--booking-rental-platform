package com.service.booking.rental.platform.exceptions;

public class BookingNotFoundException extends RuntimeException{

    public BookingNotFoundException(Long id){
        super("Book not found for the specified id: " + id);
    }

}
