package com.service.booking.rental.platform.interactors.usecase;

import com.service.booking.rental.platform.entities.Booking;
import com.service.booking.rental.platform.entities.enums.BookingStatus;
import com.service.booking.rental.platform.interactors.validate.BookingValidation;
import com.service.booking.rental.platform.repositores.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UpdateBookingUseCase {

    private final BookingRepository repository;
    private final BookingValidation bookingValidation;

    public UpdateBookingUseCase(BookingRepository repository, BookingValidation bookingValidation){
        this.repository = repository;
        this.bookingValidation = bookingValidation;
    }

    public Booking execute(Long id, Booking booking) {
        log.info("UpdateBookingUseCase - running use case update existing booking data");

        if(booking.getStatus().equals(BookingStatus.CONFIRMED)){
            bookingValidation.validateBookingUpdate(booking);
        }

        return repository.updateBookingById(id, booking);
    }

}
