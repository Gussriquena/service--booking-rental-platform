package com.service.booking.rental.platform.transportlayers.controller;

import com.service.booking.rental.platform.entities.Booking;
import com.service.booking.rental.platform.entities.enums.BookingStatus;
import com.service.booking.rental.platform.interactors.service.BookingService;
import com.service.booking.rental.platform.transportlayers.http.request.BookingCreateRequest;
import com.service.booking.rental.platform.transportlayers.http.request.BookingUpdateRequest;
import com.service.booking.rental.platform.transportlayers.http.response.BookingResponse;
import com.service.booking.rental.platform.transportlayers.mappers.BookingMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/booking")
public class BookingControllerImpl {
    private static final BookingMapper MAPPER = BookingMapper.INSTANCE;
    private final BookingService bookingService;

    public BookingControllerImpl(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponse> bookProperty(@RequestBody BookingCreateRequest request){
        Booking booking = MAPPER.map(request);
        Booking createdBooking = bookingService.bookProperty(booking);
        BookingResponse response = MAPPER.map(createdBooking);

        return ResponseEntity.status(CREATED).body(response);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<BookingResponse> cancelBooking(@PathVariable("id") Long id){
        bookingService.updateStatus(id, BookingStatus.CANCELED);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/reopen")
    public ResponseEntity<BookingResponse> reopenBooking(@PathVariable("id") Long id){
        bookingService.updateStatus(id, BookingStatus.CONFIRMED);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/close")
    public ResponseEntity<BookingResponse> closeBooking(@PathVariable("id") Long id){
        bookingService.updateStatus(id, BookingStatus.FINISHED);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getById(@PathVariable("id") Long id){
        Booking booking = bookingService.getById(id);
        BookingResponse response = MAPPER.map(booking);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponse> updateById(@PathVariable("id") Long id, @RequestBody BookingUpdateRequest request){
        Booking booking = MAPPER.map(request);
        Booking updatedBooking = bookingService.updateById(id, booking);
        BookingResponse response = MAPPER.map(updatedBooking);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        bookingService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
