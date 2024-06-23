package com.service.booking.rental.platform.repositories;

import com.service.booking.rental.platform.entities.Booking;

import java.time.LocalDate;

public interface BookingRepository {

    Booking bookProperty(Booking booking);

    Booking getBookingById(Long id);

    Booking updateBookingById(Long id, Booking booking);

    void deleteById(Long id);

    boolean hasOverlapingDates(Long idProperty, LocalDate startDate, LocalDate endDate);

    boolean hasOverlapingDatesForGuest(Booking booking);

    void updateStatus(Booking booking);
}
