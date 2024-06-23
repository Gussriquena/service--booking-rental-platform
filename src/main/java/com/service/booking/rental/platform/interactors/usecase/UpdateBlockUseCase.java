package com.service.booking.rental.platform.interactors.usecase;

import com.service.booking.rental.platform.entities.Block;
import com.service.booking.rental.platform.interactors.validate.BookingValidation;
import com.service.booking.rental.platform.repositores.BlockRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateBlockUseCase {

    private final BlockRepository repository;
    private final BookingValidation bookingValidation;

    public UpdateBlockUseCase(BlockRepository repository, BookingValidation bookingValidation) {
        this.repository = repository;
        this.bookingValidation = bookingValidation;
    }

    public Block execute(Block block){
        bookingValidation.validateBlockUpdateFeasibility(block);
        return repository.update(block);
    }
}
