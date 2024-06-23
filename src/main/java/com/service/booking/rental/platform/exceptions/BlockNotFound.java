package com.service.booking.rental.platform.exceptions;

public class BlockNotFound extends RuntimeException{
    public BlockNotFound(Long id){
        super("Block not found for the specified id: " + id);
    }
}
