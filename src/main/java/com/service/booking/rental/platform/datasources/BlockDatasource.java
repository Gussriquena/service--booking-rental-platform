package com.service.booking.rental.platform.datasources;

import com.service.booking.rental.platform.datasources.database.repository.BlockJpaRepository;
import com.service.booking.rental.platform.repositores.BlockRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BlockDatasource implements BlockRepository {

    private final BlockJpaRepository blockJpaRepository;

    public BlockDatasource(BlockJpaRepository blockJpaRepository){
        this.blockJpaRepository = blockJpaRepository;
    }

    public boolean hasBlocksByPropertyWithinDate(Long idProperty, LocalDate startDate, LocalDate endDate) {
        return blockJpaRepository.hasBlocksByPropertyWithinDate(idProperty, startDate, endDate);
    }
}
