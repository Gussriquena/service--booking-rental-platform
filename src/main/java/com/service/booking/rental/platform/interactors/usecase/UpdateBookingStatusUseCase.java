package com.service.booking.rental.platform.interactors.usecase;

import com.service.booking.rental.platform.entities.Booking;
import com.service.booking.rental.platform.entities.enums.BookingStatus;
import com.service.booking.rental.platform.interactors.validate.BookingValidation;
import com.service.booking.rental.platform.repositores.BookingRepository;
import org.springframework.stereotype.Service;

import static com.service.booking.rental.platform.entities.enums.BookingStatus.CONFIRMED;

@Service
public class UpdateBookingStatusUseCase {

    private final BookingRepository repository;
    private final BookingValidation bookingValidation;

    public UpdateBookingStatusUseCase(BookingRepository repository, BookingValidation bookingValidation){
        this.repository = repository;
        this.bookingValidation = bookingValidation;
    }

    public void execute(Long id, BookingStatus status) {
        Booking booking = repository.getBookingById(id);

        if(status.equals(CONFIRMED)){
            bookingValidation.validateBookingAvailability(booking);
        }

        booking.setStatus(status);
        repository.updateStatus(booking);
    }

}
