package com.service.booking.rental.platform.interactors.usecase;

import com.service.booking.rental.platform.entities.Block;
import com.service.booking.rental.platform.interactors.validate.BookingValidation;
import com.service.booking.rental.platform.repositores.BlockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateBlockUseCase {

    private final BlockRepository blockRepository;
    private final BookingValidation bookingValidation;

    public CreateBlockUseCase(BlockRepository blockRepository, BookingValidation bookingValidation) {
        this.blockRepository = blockRepository;
        this.bookingValidation = bookingValidation;
    }

    public Block execute(Block block) {
        log.info("CreateBlockUseCase - running use case to create new block");
        bookingValidation.validateBlockFeasibility(block);
        return blockRepository.create(block);
    }
}
