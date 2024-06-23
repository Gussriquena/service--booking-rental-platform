package com.service.booking.rental.platform.interactors.usecase;

import com.service.booking.rental.platform.entities.Block;
import com.service.booking.rental.platform.interactors.validate.BookingValidation;
import com.service.booking.rental.platform.repositories.BlockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.service.booking.rental.platform.util.BlockFactory.mockBlock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateBlockUseCaseTest {

    @InjectMocks
    CreateBlockUseCase createBlockUseCase;
    @Mock
    BlockRepository blockRepository;
    @Mock
    BookingValidation bookingValidation;

    @Test
    void when_execute_create_block_use_case_then_return_success() {
        Block block = mockBlock();

        doNothing().when(bookingValidation).validateBlockFeasibility(any(Block.class));
        when(blockRepository.create(any(Block.class))).thenReturn(block);

        Block response = createBlockUseCase.execute(block);

        assertEquals(block, response);
        verify(blockRepository).create(any(Block.class));
        verify(bookingValidation).validateBlockFeasibility(any(Block.class));
    }
}