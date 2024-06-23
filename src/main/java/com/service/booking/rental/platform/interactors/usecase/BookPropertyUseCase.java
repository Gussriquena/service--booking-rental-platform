package com.service.booking.rental.platform.interactors.usecase;

import com.service.booking.rental.platform.entities.Booking;
import com.service.booking.rental.platform.interactors.validate.BookingValidation;
import com.service.booking.rental.platform.repositories.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookPropertyUseCase {

    private final BookingRepository bookingRepository;
    private final BookingValidation bookingValidation;

    public BookPropertyUseCase(BookingRepository bookingRepository,
                               BookingValidation bookingValidation){
        this.bookingRepository = bookingRepository;
        this.bookingValidation = bookingValidation;
    }

    public Booking execute(Booking booking){
        log.info("BookPropertyUseCase - running use case to create a new booking");
        bookingValidation.validateBookingAvailability(booking);
        return bookingRepository.bookProperty(booking);
    }
}
