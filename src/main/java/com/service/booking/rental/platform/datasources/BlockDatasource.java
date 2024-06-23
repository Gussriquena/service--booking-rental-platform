package com.service.booking.rental.platform.datasources;

import com.service.booking.rental.platform.datasources.database.model.BlockEntity;
import com.service.booking.rental.platform.datasources.database.model.PropertyEntity;
import com.service.booking.rental.platform.datasources.database.repository.BlockJpaRepository;
import com.service.booking.rental.platform.datasources.database.repository.PropertyJpaRepository;
import com.service.booking.rental.platform.datasources.mapper.BlockMapper;
import com.service.booking.rental.platform.entities.Block;
import com.service.booking.rental.platform.exceptions.PropertyNotFoundException;
import com.service.booking.rental.platform.repositores.BlockRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BlockDatasource implements BlockRepository {

    private static final BlockMapper MAPPER = BlockMapper.INSTANCE;
    private final BlockJpaRepository blockJpaRepository;
    private final PropertyJpaRepository propertyJpaRepository;

    public BlockDatasource(BlockJpaRepository blockJpaRepository, PropertyJpaRepository propertyJpaRepository){
        this.blockJpaRepository = blockJpaRepository;
        this.propertyJpaRepository = propertyJpaRepository;
    }

    public boolean hasBlocksByPropertyWithinDate(Long idProperty, LocalDate startDate, LocalDate endDate) {
        return blockJpaRepository.hasBlocksByPropertyWithinDate(idProperty, startDate, endDate);
    }

    public Block create(Block block) {
        Long propertyId = block.getProperty().getId();

        PropertyEntity propertyEntity = propertyJpaRepository.findById(propertyId)
                .orElseThrow(() -> new PropertyNotFoundException(propertyId));

        BlockEntity entity = MAPPER.map(block);
        BlockEntity createdBlock = blockJpaRepository.save(entity);
        createdBlock.setProperty(propertyEntity);

        return MAPPER.map(createdBlock);
    }
}
