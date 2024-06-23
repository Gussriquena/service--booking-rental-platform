package com.service.booking.rental.platform.interactors.validate;

import com.service.booking.rental.platform.entities.Block;
import com.service.booking.rental.platform.entities.Booking;
import com.service.booking.rental.platform.exceptions.InvalidDateException;
import com.service.booking.rental.platform.exceptions.PropertyUnavailableException;
import com.service.booking.rental.platform.repositories.BlockRepository;
import com.service.booking.rental.platform.repositories.BookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static com.service.booking.rental.platform.util.BlockFactory.mockBlock;
import static com.service.booking.rental.platform.util.BookingFactory.mockBooking;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingValidationTest {

    @InjectMocks
    BookingValidation bookingValidation;

    @Mock
    BookingRepository bookingRepository;

    @Mock
    BlockRepository blockRepository;

    @Test
    void when_validate_booking_availability_then_return_success() {
        Booking booking = mockBooking();

        when(bookingRepository.hasOverlapingDates(anyLong(), any(LocalDate.class), any(LocalDate.class))).thenReturn(false);
        when(blockRepository.hasBlocksByPropertyWithinDate(anyLong(), any(LocalDate.class), any(LocalDate.class))).thenReturn(false);

        bookingValidation.validateBookingAvailability(booking);

        verify(bookingRepository).hasOverlapingDates(anyLong(), any(LocalDate.class), any(LocalDate.class));
        verify(blockRepository).hasBlocksByPropertyWithinDate(anyLong(), any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    void when_validate_booking_availability_null_date_then_throws_InvalidDateException() {
        Booking booking = mockBooking();
        booking.setStartDate(null);

        assertThrows(InvalidDateException.class, () -> bookingValidation.validateBookingAvailability(booking));

        verify(bookingRepository, never()).hasOverlapingDates(anyLong(), any(LocalDate.class), any(LocalDate.class));
        verify(blockRepository, never()).hasBlocksByPropertyWithinDate(anyLong(), any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    void when_validate_booking_availability_then_throws_InvalidDateException() {
        Booking booking = mockBooking();
        booking.setStartDate(LocalDate.of(2024, 01, 02));

        assertThrows(InvalidDateException.class, () -> bookingValidation.validateBookingAvailability(booking));

        verify(bookingRepository, never()).hasOverlapingDates(anyLong(), any(LocalDate.class), any(LocalDate.class));
        verify(blockRepository, never()).hasBlocksByPropertyWithinDate(anyLong(), any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    void when_validate_overlaping_dates_then_throws_PropertyUnavailableException() {
        Booking booking = mockBooking();

        when(bookingRepository.hasOverlapingDates(anyLong(), any(LocalDate.class), any(LocalDate.class))).thenReturn(true);

        assertThrows(PropertyUnavailableException.class, () -> bookingValidation.validateBookingAvailability(booking));

        verify(bookingRepository).hasOverlapingDates(anyLong(), any(LocalDate.class), any(LocalDate.class));
        verify(blockRepository, never()).hasBlocksByPropertyWithinDate(anyLong(), any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    void when_validate_blocked_property_then_throws_PropertyUnavailableException() {
        Booking booking = mockBooking();

        when(bookingRepository.hasOverlapingDates(anyLong(), any(LocalDate.class), any(LocalDate.class))).thenReturn(false);
        when(blockRepository.hasBlocksByPropertyWithinDate(anyLong(), any(LocalDate.class), any(LocalDate.class))).thenReturn(true);

        assertThrows(PropertyUnavailableException.class, () -> bookingValidation.validateBookingAvailability(booking));

        verify(bookingRepository).hasOverlapingDates(anyLong(), any(LocalDate.class), any(LocalDate.class));
        verify(blockRepository).hasBlocksByPropertyWithinDate(anyLong(), any(LocalDate.class), any(LocalDate.class));
    }


    @Test
    void when_validate_booking_update_then_return_success() {
        Booking booking = mockBooking();

        when(bookingRepository.hasOverlapingDatesForGuest(any(Booking.class))).thenReturn(false);
        when(blockRepository.hasBlocksByPropertyWithinDate(anyLong(), any(LocalDate.class), any(LocalDate.class))).thenReturn(false);

        bookingValidation.validateBookingUpdate(booking);

        verify(bookingRepository).hasOverlapingDatesForGuest(any(Booking.class));
        verify(blockRepository).hasBlocksByPropertyWithinDate(anyLong(), any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    void when_validate_booking_update_then_throws_PropertyUnavailableException() {
        Booking booking = mockBooking();

        when(bookingRepository.hasOverlapingDatesForGuest(any(Booking.class))).thenReturn(true);

        assertThrows(PropertyUnavailableException.class, () -> bookingValidation.validateBookingUpdate(booking));

        verify(bookingRepository).hasOverlapingDatesForGuest(any(Booking.class));
        verify(blockRepository, never()).hasBlocksByPropertyWithinDate(anyLong(), any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    void when_validate_block_feasibility_then_return_success() {
        Block block = mockBlock();

        when(blockRepository.hasBlocksByPropertyWithinDate(anyLong(), any(LocalDate.class), any(LocalDate.class))).thenReturn(false);
        when(bookingRepository.hasOverlapingDates(anyLong(), any(LocalDate.class), any(LocalDate.class))).thenReturn(false);

        bookingValidation.validateBlockFeasibility(block);

        verify(blockRepository).hasBlocksByPropertyWithinDate(anyLong(), any(LocalDate.class), any(LocalDate.class));
        verify(bookingRepository).hasOverlapingDates(anyLong(), any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    void when_validate_block_with_overlaps_then_throws_PropertyUnavailableException() {
        Block block = mockBlock();

        when(blockRepository.hasBlocksByPropertyWithinDate(anyLong(), any(LocalDate.class), any(LocalDate.class))).thenReturn(false);
        when(bookingRepository.hasOverlapingDates(anyLong(), any(LocalDate.class), any(LocalDate.class))).thenReturn(true);

        assertThrows(PropertyUnavailableException.class, () -> bookingValidation.validateBlockFeasibility(block));

        verify(blockRepository).hasBlocksByPropertyWithinDate(anyLong(), any(LocalDate.class), any(LocalDate.class));
        verify(bookingRepository).hasOverlapingDates(anyLong(), any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    void when_validate_block_update_feasibility_then_returnSuccess() {
        Block block = mockBlock();

        when(bookingRepository.hasOverlapingDates(anyLong(), any(LocalDate.class), any(LocalDate.class))).thenReturn(false);

        bookingValidation.validateBlockUpdateFeasibility(block);

        verify(bookingRepository).hasOverlapingDates(anyLong(), any(LocalDate.class), any(LocalDate.class));
    }
}