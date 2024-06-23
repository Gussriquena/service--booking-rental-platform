package com.service.booking.rental.platform.util;

import com.service.booking.rental.platform.datasources.database.model.BookingEntity;
import com.service.booking.rental.platform.entities.Booking;
import com.service.booking.rental.platform.entities.enums.BookingStatus;
import com.service.booking.rental.platform.transportlayers.http.request.BookingCreateRequest;
import com.service.booking.rental.platform.transportlayers.http.request.BookingUpdateRequest;
import com.service.booking.rental.platform.transportlayers.http.response.BookingResponse;

import java.time.LocalDate;

import static com.service.booking.rental.platform.entities.enums.BookingStatus.CONFIRMED;
import static com.service.booking.rental.platform.util.PropertyFactory.*;
import static com.service.booking.rental.platform.util.PropertyUserFactory.*;

public class BookingFactory {


    public static Booking mockBooking(){
        return Booking.builder()
                .id(1L)
                .startDate(LocalDate.of(2024, 7, 22))
                .endDate(LocalDate.of(2024, 7, 24))
                .guest(mockGuestPropertyUser())
                .property(mockProperty())
                .status(CONFIRMED)
                .build();
    }

    public static BookingEntity mockBookingEntity(){
        return BookingEntity.builder()
                .id(1L)
                .startDate(LocalDate.of(2024, 7, 22))
                .endDate(LocalDate.of(2024, 7, 24))
                .guest(mockGuestPropertyUserEntity())
                .property(mockPropertyEntity())
                .status(CONFIRMED)
                .build();
    }

    public static BookingCreateRequest mockBookingCreateRequest(){
        return BookingCreateRequest.builder()
                .startDate(LocalDate.of(2024, 7, 22))
                .endDate(LocalDate.of(2024, 7, 24))
                .idGuest(1L)
                .idProperty(1L)
                .build();
    }

    public static BookingResponse mockBookingResponse(){
        return BookingResponse.builder()
                .id(1L)
                .startDate(LocalDate.of(2024, 7, 22))
                .endDate(LocalDate.of(2024, 7, 24))
                .guest(mockGuestPropertyUserResponse())
                .property(mockPropertyResponse())
                .status(CONFIRMED.toString())
                .build();
    }
    public static BookingUpdateRequest mockBookingUpdateRequest(){
        return BookingUpdateRequest.builder()
                .id(1L)
                .startDate(LocalDate.of(2024, 7, 22))
                .endDate(LocalDate.of(2024, 7, 24))
                .guest(mockGuestPropertyUserRequest())
                .idProperty(1L)
                .status(CONFIRMED.toString())
                .build();
    }



}
