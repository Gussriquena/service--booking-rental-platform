package com.service.booking.rental.platform.transportlayers.controller;

import com.service.booking.rental.platform.entities.Booking;
import com.service.booking.rental.platform.interactors.service.BookingService;
import com.service.booking.rental.platform.transportlayers.http.request.BookingRequest;
import com.service.booking.rental.platform.transportlayers.http.response.BookingResponse;
import com.service.booking.rental.platform.transportlayers.mappers.BookingMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingControllerImpl {

    private final BookingService bookingService;
    private static final BookingMapper MAPPER = BookingMapper.INSTANCE;

    public BookingControllerImpl(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<Void> bookProperty(@RequestBody BookingRequest bookingRequest){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getById(@PathVariable("id") Long id){
        Booking booking = bookingService.getById(id);
        BookingResponse response = MAPPER.map(booking);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponse> updateById(@PathVariable("id") Long id, @RequestBody BookingRequest request){
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
