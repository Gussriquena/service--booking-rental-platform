package com.service.booking.rental.platform.datasources;

import com.service.booking.rental.platform.datasources.database.model.BlockEntity;
import com.service.booking.rental.platform.datasources.database.model.PropertyEntity;
import com.service.booking.rental.platform.datasources.database.repository.BlockJpaRepository;
import com.service.booking.rental.platform.datasources.database.repository.PropertyJpaRepository;
import com.service.booking.rental.platform.datasources.mapper.BlockMapper;
import com.service.booking.rental.platform.entities.Block;
import com.service.booking.rental.platform.exceptions.BlockNotFound;
import com.service.booking.rental.platform.exceptions.PropertyNotFoundException;
import com.service.booking.rental.platform.repositories.BlockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Slf4j
public class BlockDatasource implements BlockRepository {

    private static final BlockMapper MAPPER = BlockMapper.INSTANCE;
    private final BlockJpaRepository blockJpaRepository;
    private final PropertyJpaRepository propertyJpaRepository;

    public BlockDatasource(BlockJpaRepository blockJpaRepository, PropertyJpaRepository propertyJpaRepository){
        this.blockJpaRepository = blockJpaRepository;
        this.propertyJpaRepository = propertyJpaRepository;
    }

    public boolean hasBlocksByPropertyWithinDate(Long idProperty, LocalDate startDate, LocalDate endDate) {
        log.info("BlockDatasource - SQL - checking for blocks with parameters - [idProperty, startDate, endDate]");
        return blockJpaRepository.hasBlocksByPropertyWithinDate(idProperty, startDate, endDate);
    }

    public Block create(Block block) {
        log.info("BlockDatasource - creating new block on database");

        Long idProperty = block.getProperty().getId();

        PropertyEntity propertyEntity = propertyJpaRepository.findById(idProperty)
                .orElseThrow(() -> new PropertyNotFoundException(idProperty));

        BlockEntity entity = MAPPER.map(block);
        BlockEntity createdBlock = blockJpaRepository.save(entity);
        createdBlock.setProperty(propertyEntity);

        return MAPPER.map(createdBlock);
    }

    public Block update(Block block) {
        log.info("BlockDatasource - updating block on database");

        Long idProperty = block.getProperty().getId();

        PropertyEntity propertyEntity = propertyJpaRepository.findById(idProperty)
                .orElseThrow(() -> new PropertyNotFoundException(idProperty));

        BlockEntity entity = MAPPER.map(block);
        BlockEntity updatedBlock = blockJpaRepository.save(entity);
        updatedBlock.setProperty(propertyEntity);

        return MAPPER.map(updatedBlock);
    }

    public void delete(Long id) {
        log.info("BlockDatasource - SQL - deleting block on database");
        blockJpaRepository.deleteById(id);
    }

    public Block get(Long id) {
        log.info("BlockDatasource - retrieving Block by id");

        BlockEntity entity = blockJpaRepository.findById(id).orElseThrow(() -> new BlockNotFound(id));
        return MAPPER.map(entity);
    }

    public List<Block> listByProperty(Long idProperty) {
        log.info("BlockDatasource - listing all blocks by property found on database");

        PropertyEntity propertyEntity = propertyJpaRepository.findById(idProperty)
                .orElseThrow(() -> new PropertyNotFoundException(idProperty));

        List<BlockEntity> entity = blockJpaRepository.findByProperty(propertyEntity);

        return MAPPER.map(entity);
    }
}
