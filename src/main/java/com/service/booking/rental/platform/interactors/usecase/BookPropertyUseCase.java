package com.service.booking.rental.platform.interactors.usecase;

import com.service.booking.rental.platform.entities.Booking;
import com.service.booking.rental.platform.repositores.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class BookPropertyUseCase {

    private final BookingRepository repository;

    public BookPropertyUseCase(BookingRepository repository){
        this.repository = repository;
    }

    public Booking execute(Booking booking){
        // TODO: VALIDADE BOOKINGS FOR THIS PROPERTY
        // TODO: VALIDADE BLOCKS FOR THIS PROPERTY


        return repository.bookProperty(booking);
    }

}
