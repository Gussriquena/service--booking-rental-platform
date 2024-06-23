package com.service.booking.rental.platform.transportlayers.controller;

import com.service.booking.rental.platform.entities.Block;
import com.service.booking.rental.platform.interactors.service.BlockService;
import com.service.booking.rental.platform.transportlayers.http.request.BlockRequest;
import com.service.booking.rental.platform.transportlayers.http.response.BlockResponse;
import com.service.booking.rental.platform.transportlayers.mappers.BlockMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/block")
@Slf4j
public class BlockControllerImpl {

    private static final BlockMapper MAPPER = BlockMapper.INSTANCE;
    private final BlockService blockService;

    public BlockControllerImpl(BlockService blockService){
        this.blockService = blockService;
    }

    @PostMapping
    public ResponseEntity<BlockResponse> create(@RequestBody BlockRequest request){
        log.info("POST - creating block - {}", request);

        Block block = MAPPER.map(request);
        Block createdBlock = blockService.create(block);
        BlockResponse response = MAPPER.map(createdBlock);
        return ResponseEntity.status(CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<BlockResponse>> listByPropertyId(@Param("idProperty") Long idProperty){
        log.info("GET - listing blocks to property with id - {}", idProperty);

        List<Block> block = blockService.listByPropertyId(idProperty);
        List<BlockResponse> response = MAPPER.map(block);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlockResponse> getById(@PathVariable("id") Long id){
        log.info("GET - searching block by id - {}", id);

        Block block = blockService.getById(id);
        BlockResponse response = MAPPER.map(block);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlockResponse> updateById(@PathVariable("id") Long id, @RequestBody BlockRequest request){
        log.info("PUT - updating block with id {}: - {}", id, request);

        Block block = MAPPER.map(request);
        Block updatedBlock = blockService.updateById(block);
        BlockResponse response = MAPPER.map(updatedBlock);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        log.info("DELETE - excluding block with id {}", id);

        blockService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
