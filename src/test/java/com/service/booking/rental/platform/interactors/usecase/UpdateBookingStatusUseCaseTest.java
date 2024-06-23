package com.service.booking.rental.platform.interactors.usecase;

import com.service.booking.rental.platform.entities.Booking;
import com.service.booking.rental.platform.entities.enums.BookingStatus;
import com.service.booking.rental.platform.interactors.validate.BookingValidation;
import com.service.booking.rental.platform.repositories.BookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.service.booking.rental.platform.util.BookingFactory.mockBooking;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdateBookingStatusUseCaseTest {

    @InjectMocks
    UpdateBookingStatusUseCase updateBookingStatusUseCase;
    @Mock
    BookingRepository bookingRepository;

    @Mock
    BookingValidation bookingValidation;

    @Test
    void when_execute_update_booking_status_use_case_then_return_success() {
        Booking booking = mockBooking();

        when(bookingRepository.getBookingById(anyLong())).thenReturn(booking);

        updateBookingStatusUseCase.execute(1L, BookingStatus.CONFIRMED);

        verify(bookingRepository).getBookingById(anyLong());
        verify(bookingRepository).updateStatus(any(Booking.class));
    }

}