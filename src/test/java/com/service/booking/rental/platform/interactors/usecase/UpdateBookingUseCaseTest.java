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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateBookingUseCaseTest {

    @InjectMocks
    UpdateBookingUseCase updateBookingUseCase;
    @Mock
    BookingRepository bookingRepository;

    @Mock
    BookingValidation bookingValidation;

    @Test
    void when_execute_update_booking_use_case_then_return_success() {
        Booking booking = mockBooking();

        when(bookingRepository.updateBookingById(anyLong(), any(Booking.class))).thenReturn(booking);

        updateBookingUseCase.execute(1L, booking);

        verify(bookingRepository).updateBookingById(anyLong(), any(Booking.class));
    }
}