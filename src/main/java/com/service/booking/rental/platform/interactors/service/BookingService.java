package com.service.booking.rental.platform.interactors.service;

import com.service.booking.rental.platform.entities.Booking;
import com.service.booking.rental.platform.entities.enums.BookingStatus;
import com.service.booking.rental.platform.interactors.usecase.BookPropertyUseCase;
import com.service.booking.rental.platform.interactors.usecase.UpdateBookingStatusUseCase;
import com.service.booking.rental.platform.interactors.usecase.UpdateBookingUseCase;
import com.service.booking.rental.platform.repositores.BookingRepository;
import org.springframework.stereotype.Service;


@Service
public class BookingService {

    private final BookingRepository repository;
    private final BookPropertyUseCase bookPropertyUseCase;
    private final UpdateBookingUseCase updateBookingUseCase;
    private final UpdateBookingStatusUseCase updateBookingStatusUseCase;

    public BookingService(BookingRepository repository,
                          BookPropertyUseCase bookPropertyUseCase,
                          UpdateBookingUseCase updateBookingUseCase,
                          UpdateBookingStatusUseCase updateBookingStatusUseCase){
        this.repository = repository;
        this.bookPropertyUseCase = bookPropertyUseCase;
        this.updateBookingUseCase = updateBookingUseCase;
        this.updateBookingStatusUseCase = updateBookingStatusUseCase;
    }

    public Booking getById(Long id){
        return repository.getBookingById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void updateStatus(Long id, BookingStatus status) {
        updateBookingStatusUseCase.execute(id, status);
    }

    public Booking bookProperty(Booking booking){
        return bookPropertyUseCase.execute(booking);
    }

    public Booking updateById(Long id, Booking booking){
        return updateBookingUseCase.execute(id, booking);
    }
}
