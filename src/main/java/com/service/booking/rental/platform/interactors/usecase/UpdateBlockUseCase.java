package com.service.booking.rental.platform.interactors.usecase;

import com.service.booking.rental.platform.entities.Block;
import com.service.booking.rental.platform.interactors.validate.BookingValidation;
import com.service.booking.rental.platform.repositories.BlockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UpdateBlockUseCase {

    private final BlockRepository repository;
    private final BookingValidation bookingValidation;

    public UpdateBlockUseCase(BlockRepository repository, BookingValidation bookingValidation) {
        this.repository = repository;
        this.bookingValidation = bookingValidation;
    }

    public Block execute(Block block){
        log.info("UpdateBlockUseCase - running use case to update existing block");
        bookingValidation.validateBlockUpdateFeasibility(block);
        return repository.update(block);
    }
}
