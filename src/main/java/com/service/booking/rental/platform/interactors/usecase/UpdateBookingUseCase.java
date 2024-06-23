package com.service.booking.rental.platform.interactors.usecase;

import com.service.booking.rental.platform.entities.Booking;
import com.service.booking.rental.platform.entities.enums.BookingStatus;
import com.service.booking.rental.platform.interactors.validate.BookingValidation;
import com.service.booking.rental.platform.repositores.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateBookingUseCase {

    private final BookingRepository repository;
    private final BookingValidation bookingValidation;

    public UpdateBookingUseCase(BookingRepository repository, BookingValidation bookingValidation){
        this.repository = repository;
        this.bookingValidation = bookingValidation;
    }

    public Booking execute(Long id, Booking booking) {
        if(booking.getStatus().equals(BookingStatus.CONFIRMED)){
            bookingValidation.validateBookingUpdate(booking);
        }

        return repository.updateBookingById(id, booking);
    }

}
