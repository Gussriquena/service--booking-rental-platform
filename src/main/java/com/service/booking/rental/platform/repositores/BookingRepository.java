package com.service.booking.rental.platform.repositores;

import com.service.booking.rental.platform.entities.Booking;

public interface BookingRepository {

    Booking bookProperty(Booking booking);

    Booking getBookingById(Long id);

    Booking updateBookingById(Long id, Booking booking);

    void deleteById(Long id);

   boolean hasOverlapingDates(Booking booking);

    boolean hasOverlapingDatesForGuest(Booking booking);

    void updateStatus(Booking booking);
}
