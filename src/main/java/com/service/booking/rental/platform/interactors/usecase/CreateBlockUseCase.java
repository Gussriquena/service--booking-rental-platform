package com.service.booking.rental.platform.interactors.usecase;

import com.service.booking.rental.platform.entities.Block;
import com.service.booking.rental.platform.interactors.validate.BookingValidation;
import com.service.booking.rental.platform.repositores.BlockRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateBlockUseCase {

    private final BlockRepository blockRepository;
    private final BookingValidation bookingValidation;

    public CreateBlockUseCase(BlockRepository blockRepository, BookingValidation bookingValidation) {
        this.blockRepository = blockRepository;
        this.bookingValidation = bookingValidation;
    }

    public Block execute(Block block) {
        bookingValidation.validateBlockFeasibility(block);
        return blockRepository.create(block);
    }
}
