package com.service.booking.rental.platform.repositories;

import com.service.booking.rental.platform.entities.Block;

import java.time.LocalDate;
import java.util.List;

public interface BlockRepository {

    boolean hasBlocksByPropertyWithinDate(Long idProperty, LocalDate startDate, LocalDate endDate);
    Block create(Block block);
    Block update(Block block);
    void delete(Long id);
    Block get(Long id);
    List<Block> listByProperty(Long idProperty);
}
