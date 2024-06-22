package com.service.booking.rental.platform.transportlayers.controller;

import com.service.booking.rental.platform.transportlayers.http.request.BlockRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/block")
public class BlockControllerImpl {

    @PostMapping
    public ResponseEntity<Void> createBlock(@RequestBody BlockRequest blockRequest){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> getBlockById(@PathVariable("id") Long id){
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBlock(@PathVariable("id") Long id){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlock(@PathVariable("id") Long id){
        return ResponseEntity.ok().build();
    }


}
