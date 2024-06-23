package com.service.booking.rental.platform.repositores;

import com.service.booking.rental.platform.entities.Block;

import java.time.LocalDate;

public interface BlockRepository {

    boolean hasBlocksByPropertyWithinDate(Long idProperty, LocalDate startDate, LocalDate endDate);
    Block create(Block block);
}
