package com.service.booking.rental.platform.util;

import com.service.booking.rental.platform.datasources.database.model.BookingEntity;
import com.service.booking.rental.platform.entities.Booking;
import com.service.booking.rental.platform.entities.enums.BookingStatus;

import java.time.LocalDate;

import static com.service.booking.rental.platform.util.PropertyFactory.mockProperty;
import static com.service.booking.rental.platform.util.PropertyFactory.mockPropertyEntity;
import static com.service.booking.rental.platform.util.PropertyUserFactory.mockGuestPropertyUser;
import static com.service.booking.rental.platform.util.PropertyUserFactory.mockGuestPropertyUserEntity;

public class BookingFactory {


    public static Booking mockBooking(){
        return Booking.builder()
                .id(1L)
                .startDate(LocalDate.of(2024, 7, 22))
                .endDate(LocalDate.of(2024, 7, 24))
                .guest(mockGuestPropertyUser())
                .property(mockProperty())
                .status(BookingStatus.CONFIRMED)
                .build();
    }

    public static BookingEntity mockBookingEntity(){
        return BookingEntity.builder()
                .id(1L)
                .startDate(LocalDate.of(2024, 7, 22))
                .endDate(LocalDate.of(2024, 7, 24))
                .guest(mockGuestPropertyUserEntity())
                .property(mockPropertyEntity())
                .status(BookingStatus.CONFIRMED)
                .build();
    }

}
