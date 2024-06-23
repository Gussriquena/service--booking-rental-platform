package com.service.booking.rental.platform.interactors.service;

import com.service.booking.rental.platform.entities.Block;
import com.service.booking.rental.platform.interactors.usecase.CreateBlockUseCase;
import com.service.booking.rental.platform.repositores.BlockRepository;
import org.springframework.stereotype.Service;

@Service
public class BlockService {

    private final BlockRepository repository;
    private final CreateBlockUseCase createBlockUseCase;

    public BlockService(BlockRepository repository, CreateBlockUseCase createBlockUseCase){
        this.repository = repository;
        this.createBlockUseCase = createBlockUseCase;
    }

    public Block create(Block block) {
        return createBlockUseCase.execute(block);
    }
}
