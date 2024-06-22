package com.service.booking.rental.platform.transportlayers.controller;

import com.service.booking.rental.platform.transportlayers.http.request.BookingRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingControllerImpl {

    @PostMapping
    public ResponseEntity<Void> bookProperty(@RequestBody BookingRequest bookingRequest){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> getBookingById(@PathVariable("id") Long id){
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBooking(@PathVariable("id") Long id){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable("id") Long id){
        return ResponseEntity.ok().build();
    }

}
