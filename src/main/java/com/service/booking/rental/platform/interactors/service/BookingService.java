package com.service.booking.rental.platform.interactors.service;

import com.service.booking.rental.platform.entities.Booking;
import com.service.booking.rental.platform.repositores.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository repository;

    public BookingService(BookingRepository repository){
        this.repository = repository;
    }

    public Booking getById(Long id){
        return repository.getBookingById(id);
    }

    public Booking updateById(Long id, Booking booking) {
        return repository.updateBookingById(id, booking);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
