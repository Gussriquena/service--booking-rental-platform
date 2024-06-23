package com.service.booking.rental.platform.transportlayers.controller;

import com.service.booking.rental.platform.entities.Block;
import com.service.booking.rental.platform.interactors.service.BlockService;
import com.service.booking.rental.platform.transportlayers.http.request.BlockRequest;
import com.service.booking.rental.platform.transportlayers.http.response.BlockResponse;
import com.service.booking.rental.platform.transportlayers.mappers.BlockMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/block")
public class BlockControllerImpl {

    private static final BlockMapper MAPPER = BlockMapper.INSTANCE;
    private final BlockService blockService;

    public BlockControllerImpl(BlockService blockService){
        this.blockService = blockService;
    }

    @PostMapping
    public ResponseEntity<BlockResponse> create(@RequestBody BlockRequest blockRequest){
        Block block = MAPPER.map(blockRequest);
        Block createdBlock = blockService.create(block);
        BlockResponse response = MAPPER.map(createdBlock);
        return ResponseEntity.status(CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Void> getByPropertyId(@Param("idProperty") Long idProperty){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> getById(@PathVariable("id") Long id){

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok().build();
    }


}
