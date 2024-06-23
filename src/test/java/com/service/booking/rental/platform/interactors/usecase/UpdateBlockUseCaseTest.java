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
class UpdateBlockUseCaseTest {

    @InjectMocks
    UpdateBlockUseCase updateBlockUseCase;
    @Mock
    BlockRepository blockRepository;
    @Mock
    BookingValidation bookingValidation;

    @Test
    void when_execute_update_block_use_case_then_return_success() {
        Block block = mockBlock();

        doNothing().when(bookingValidation).validateBlockUpdateFeasibility(any(Block.class));
        when(blockRepository.update(any(Block.class))).thenReturn(block);

        Block response = updateBlockUseCase.execute(block);

        assertEquals(block, response);
        verify(blockRepository).update(any(Block.class));
        verify(bookingValidation).validateBlockUpdateFeasibility(any(Block.class));
    }
}