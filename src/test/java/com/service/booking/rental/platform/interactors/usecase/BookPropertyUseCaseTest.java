package com.service.booking.rental.platform.interactors.usecase;

import com.service.booking.rental.platform.entities.Booking;
import com.service.booking.rental.platform.interactors.validate.BookingValidation;
import com.service.booking.rental.platform.repositories.BookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.print.Book;

import static com.service.booking.rental.platform.util.BookingFactory.mockBooking;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookPropertyUseCaseTest {

    @InjectMocks
    BookPropertyUseCase bookPropertyUseCase;

    @Mock
    BookingValidation bookingValidation;

    @Mock
    BookingRepository bookingRepository;

    @Test
    void when_execute_use_case_then_return_success() {
        Booking booking = mockBooking();

        doNothing().when(bookingValidation).validateBookingAvailability(any(Booking.class));
        when(bookingRepository.bookProperty(any(Booking.class))).thenReturn(booking);

        Booking response = bookPropertyUseCase.execute(booking);

        assertEquals(booking, response);
        verify(bookingRepository).bookProperty(any(Booking.class));
        verify(bookingValidation).validateBookingAvailability(any(Booking.class));
    }
}