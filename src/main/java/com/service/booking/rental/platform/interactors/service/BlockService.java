package com.service.booking.rental.platform.interactors.service;

import com.service.booking.rental.platform.entities.Block;
import com.service.booking.rental.platform.interactors.usecase.CreateBlockUseCase;
import com.service.booking.rental.platform.interactors.usecase.UpdateBlockUseCase;
import com.service.booking.rental.platform.repositores.BlockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockService {

    private final BlockRepository repository;
    private final CreateBlockUseCase createBlockUseCase;
    private final UpdateBlockUseCase updateBlockUseCase;

    public BlockService(BlockRepository repository, CreateBlockUseCase createBlockUseCase, UpdateBlockUseCase updateBlockUseCase){
        this.repository = repository;
        this.createBlockUseCase = createBlockUseCase;
        this.updateBlockUseCase = updateBlockUseCase;
    }

    public Block create(Block block) {
        return createBlockUseCase.execute(block);
    }

    public Block updateById(Block block) {
        return updateBlockUseCase.execute(block);
    }

    public void deleteById(Long id) {
        repository.delete(id);
    }

    public Block getById(Long id) {
        return repository.get(id);
    }

    public List<Block> listByPropertyId(Long idProperty) {
        return repository.listByProperty(idProperty);
    }
}
