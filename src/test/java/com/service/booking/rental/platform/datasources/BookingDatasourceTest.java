package com.service.booking.rental.platform.datasources;

import com.service.booking.rental.platform.datasources.database.model.BookingEntity;
import com.service.booking.rental.platform.datasources.database.model.PropertyEntity;
import com.service.booking.rental.platform.datasources.database.model.PropertyUserEntity;
import com.service.booking.rental.platform.datasources.database.repository.BookingJpaRepository;
import com.service.booking.rental.platform.datasources.database.repository.PropertyJpaRepository;
import com.service.booking.rental.platform.datasources.database.repository.PropertyUserJpaRepository;
import com.service.booking.rental.platform.entities.Booking;
import com.service.booking.rental.platform.exceptions.BookingNotFoundException;
import com.service.booking.rental.platform.exceptions.PropertyNotFoundException;
import com.service.booking.rental.platform.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static com.service.booking.rental.platform.util.BookingFactory.mockBooking;
import static com.service.booking.rental.platform.util.BookingFactory.mockBookingEntity;
import static com.service.booking.rental.platform.util.Constants.*;
import static com.service.booking.rental.platform.util.Constants.END_DATE;
import static com.service.booking.rental.platform.util.PropertyFactory.mockPropertyEntity;
import static com.service.booking.rental.platform.util.PropertyUserFactory.mockGuestPropertyUserEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingDatasourceTest {

    @InjectMocks
    BookingDatasource bookingDatasource;
    @Mock
    PropertyUserJpaRepository propertyUserJpaRepository;
    @Mock
    PropertyJpaRepository propertyJpaRepository;
    @Mock
    BookingJpaRepository bookingJpaRepository;


    @Test
    void when_book_property_then_return_success() {
        PropertyEntity propertyEntity = mockPropertyEntity();
        PropertyUserEntity propertyUserEntity = mockGuestPropertyUserEntity();
        BookingEntity bookingEntity = mockBookingEntity();

        Booking booking = mockBooking();

        when(propertyJpaRepository.findById(anyLong())).thenReturn(Optional.of(propertyEntity));
        when(propertyUserJpaRepository.findById(anyLong())).thenReturn(Optional.of(propertyUserEntity));
        when(bookingJpaRepository.save(any(BookingEntity.class))).thenReturn(bookingEntity);

        Booking response = bookingDatasource.bookProperty(booking);

        assertEquals(booking, response);
        verify(propertyJpaRepository).findById(anyLong());
        verify(propertyUserJpaRepository).findById(anyLong());
        verify(bookingJpaRepository).save(any(BookingEntity.class));
    }

    @Test
    void when_book_property_then_throws_UserNotFoundException() {
        Booking booking = mockBooking();

        when(propertyUserJpaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> bookingDatasource.bookProperty(booking));

        verify(propertyUserJpaRepository).findById(anyLong());
        verify(propertyJpaRepository, never()).findById(anyLong());
        verify(bookingJpaRepository, never()).save(any(BookingEntity.class));
    }

    @Test
    void when_book_property_then_throws_PropertyNotFoundException() {
        PropertyUserEntity propertyUserEntity = mockGuestPropertyUserEntity();
        Booking booking = mockBooking();

        when(propertyUserJpaRepository.findById(anyLong())).thenReturn(Optional.of(propertyUserEntity));
        when(propertyJpaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(PropertyNotFoundException.class, () -> bookingDatasource.bookProperty(booking));

        verify(propertyUserJpaRepository).findById(anyLong());
        verify(propertyJpaRepository).findById(anyLong());
        verify(bookingJpaRepository, never()).save(any(BookingEntity.class));
    }

    @Test
    void when_get_booking_by_id_then_return_success() {
        BookingEntity bookingEntity = mockBookingEntity();
        Booking booking = mockBooking();

        when(bookingJpaRepository.findById(anyLong())).thenReturn(Optional.of(bookingEntity));

        Booking response = bookingDatasource.getBookingById(1L);

        assertEquals(booking, response);
        verify(bookingJpaRepository).findById(anyLong());
    }

    @Test
    void when_get_booking_by_id_then_throws_BookingNotFoundException() {
        when(bookingJpaRepository.findById(anyLong())).thenThrow(new BookingNotFoundException(2L));

        assertThrows(BookingNotFoundException.class, () -> bookingDatasource.getBookingById(2L));

        verify(bookingJpaRepository).findById(anyLong());
    }

    @Test
    void when_update_booking_by_id_then_return_success() {
        PropertyEntity propertyEntity = mockPropertyEntity();
        PropertyUserEntity propertyUserEntity = mockGuestPropertyUserEntity();
        BookingEntity bookingEntity = mockBookingEntity();

        Booking booking = mockBooking();

        when(bookingJpaRepository.findById(anyLong())).thenReturn(Optional.of(bookingEntity));
        when(propertyUserJpaRepository.findById(anyLong())).thenReturn(Optional.of(propertyUserEntity));
        when(propertyJpaRepository.findById(anyLong())).thenReturn(Optional.of(propertyEntity));

        when(bookingJpaRepository.save(any(BookingEntity.class))).thenReturn(bookingEntity);
        when(propertyUserJpaRepository.save(any(PropertyUserEntity.class))).thenReturn(propertyUserEntity);

        Booking response = bookingDatasource.updateBookingById(1L, booking);

        assertEquals(booking, response);
        verify(bookingJpaRepository).findById(anyLong());
        verify(propertyUserJpaRepository).findById(anyLong());
        verify(propertyJpaRepository).findById(anyLong());
        verify(propertyUserJpaRepository).save(any(PropertyUserEntity.class));
        verify(bookingJpaRepository).save(any(BookingEntity.class));
    }

    @Test
    void when_update_booking_by_id_then_throws_BookingNotFoundException() {
        Booking booking = mockBooking();

        when(bookingJpaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(BookingNotFoundException.class, () -> bookingDatasource.updateBookingById(1L, booking));

        verify(bookingJpaRepository).findById(anyLong());
        verify(propertyUserJpaRepository, never()).findById(anyLong());
        verify(propertyJpaRepository, never()).findById(anyLong());
        verify(propertyUserJpaRepository, never()).save(any(PropertyUserEntity.class));
        verify(bookingJpaRepository, never()).save(any(BookingEntity.class));
    }

    @Test
    void when_update_booking_by_id_then_throws_UserNotFoundException() {
        BookingEntity bookingEntity = mockBookingEntity();
        Booking booking = mockBooking();

        when(bookingJpaRepository.findById(anyLong())).thenReturn(Optional.of(bookingEntity));
        when(propertyUserJpaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> bookingDatasource.updateBookingById(1L, booking));

        verify(bookingJpaRepository).findById(anyLong());
        verify(propertyUserJpaRepository).findById(anyLong());
        verify(propertyJpaRepository, never()).findById(anyLong());
        verify(propertyUserJpaRepository, never()).save(any(PropertyUserEntity.class));
        verify(bookingJpaRepository, never()).save(any(BookingEntity.class));
    }

    @Test
    void when_update_booking_by_id_then_throws_PropertyNotFoundException() {
        PropertyUserEntity propertyUserEntity = mockGuestPropertyUserEntity();
        BookingEntity bookingEntity = mockBookingEntity();
        Booking booking = mockBooking();

        when(bookingJpaRepository.findById(anyLong())).thenReturn(Optional.of(bookingEntity));
        when(propertyUserJpaRepository.findById(anyLong())).thenReturn(Optional.of(propertyUserEntity));
        when(propertyJpaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(PropertyNotFoundException.class, () -> bookingDatasource.updateBookingById(1L, booking));

        verify(bookingJpaRepository).findById(anyLong());
        verify(propertyUserJpaRepository).findById(anyLong());
        verify(propertyJpaRepository).findById(anyLong());
        verify(propertyUserJpaRepository, never()).save(any(PropertyUserEntity.class));
        verify(bookingJpaRepository, never()).save(any(BookingEntity.class));
    }


    @Test
    void when_delete_by_id_then_return_success() {
        doNothing().when(bookingJpaRepository).deleteById(anyLong());

        bookingDatasource.deleteById(1L);

        verify(bookingJpaRepository).deleteById(anyLong());
    }

    @Test
    void when_verify_overlaping_dates_then_return_success() {
        when(bookingJpaRepository.hasOverlapingDates(anyLong(), any(LocalDate.class), any(LocalDate.class))).thenReturn(false);

        boolean result = bookingDatasource.hasOverlapingDates(ID_PROPERTY, START_DATE, END_DATE);

        assertFalse(result);
        verify(bookingJpaRepository).hasOverlapingDates(ID_PROPERTY, START_DATE, END_DATE);
    }

    @Test
    void when_verify_overlaping_dates_for_guest_then_return_success() {
        Booking booking = mockBooking();

        when(bookingJpaRepository.hasOverlapingDatesForGuest(anyLong(), anyLong(), any(LocalDate.class), any(LocalDate.class))).thenReturn(false);

        boolean result = bookingDatasource.hasOverlapingDatesForGuest(booking);

        assertFalse(result);
        verify(bookingJpaRepository).hasOverlapingDatesForGuest(anyLong(), anyLong(), any(LocalDate.class), any(LocalDate.class));

    }

    @Test
    void when_update_status_then_return_success() {
        BookingEntity bookingEntity = mockBookingEntity();
        Booking booking = mockBooking();

        when(bookingJpaRepository.save(any(BookingEntity.class))).thenReturn(bookingEntity);

        bookingDatasource.updateStatus(booking);

        verify(bookingJpaRepository).save(any(BookingEntity.class));
    }
}