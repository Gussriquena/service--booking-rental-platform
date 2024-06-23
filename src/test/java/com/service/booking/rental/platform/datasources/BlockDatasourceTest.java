package com.service.booking.rental.platform.datasources;

import com.service.booking.rental.platform.datasources.database.model.BlockEntity;
import com.service.booking.rental.platform.datasources.database.model.PropertyEntity;
import com.service.booking.rental.platform.datasources.database.repository.BlockJpaRepository;
import com.service.booking.rental.platform.datasources.database.repository.PropertyJpaRepository;
import com.service.booking.rental.platform.entities.Block;
import com.service.booking.rental.platform.exceptions.BlockNotFound;
import com.service.booking.rental.platform.exceptions.PropertyNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.service.booking.rental.platform.util.BlockFactory.mockBlock;
import static com.service.booking.rental.platform.util.BlockFactory.mockBlockEntity;
import static com.service.booking.rental.platform.util.Constants.*;
import static com.service.booking.rental.platform.util.PropertyFactory.mockPropertyEntity;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlockDatasourceTest {

    @InjectMocks
    BlockDatasource blockDatasource;
    @Mock
    BlockJpaRepository blockJpaRepository;
    @Mock
    PropertyJpaRepository propertyJpaRepository;

    @Test
    void when_verify_blocks_by_property_then_return_success() {
        when(blockJpaRepository.hasBlocksByPropertyWithinDate(anyLong(), any(LocalDate.class), any(LocalDate.class))).thenReturn(false);

        boolean result = blockDatasource.hasBlocksByPropertyWithinDate(ID_PROPERTY, START_DATE, END_DATE);

        assertFalse(result);
        verify(blockJpaRepository).hasBlocksByPropertyWithinDate(ID_PROPERTY, START_DATE, END_DATE);
    }

    @Test
    void when_create_new_block_then_return_success() {
        PropertyEntity propertyEntity = mockPropertyEntity();
        BlockEntity blockEntity = mockBlockEntity();
        Block block = mockBlock();

        when(propertyJpaRepository.findById(anyLong())).thenReturn(Optional.of(propertyEntity));
        when(blockJpaRepository.save(any(BlockEntity.class))).thenReturn(blockEntity);

        Block blockResponse = blockDatasource.create(block);

        assertEquals(block, blockResponse);
        verify(propertyJpaRepository).findById(anyLong());
        verify(blockJpaRepository).save(any(BlockEntity.class));
    }

    @Test
    void when_create_new_block_then_throws_PropertyNotFoundException() {
        Block block = mockBlock();

        when(propertyJpaRepository.findById(anyLong())).thenThrow(new PropertyNotFoundException(2L));

        assertThrows(PropertyNotFoundException.class, () -> blockDatasource.create(block));

        verify(propertyJpaRepository).findById(anyLong());
        verify(blockJpaRepository, never()).save(any(BlockEntity.class));
    }

    @Test
    void when_update_existing_block_then_return_success() {
        PropertyEntity propertyEntity = mockPropertyEntity();
        BlockEntity blockEntity = mockBlockEntity();
        Block block = mockBlock();

        when(propertyJpaRepository.findById(anyLong())).thenReturn(Optional.of(propertyEntity));
        when(blockJpaRepository.save(any(BlockEntity.class))).thenReturn(blockEntity);

        Block blockResponse = blockDatasource.update(block);

        assertEquals(block, blockResponse);
        verify(propertyJpaRepository).findById(anyLong());
        verify(blockJpaRepository).save(any(BlockEntity.class));
    }

    @Test
    void when_update_existing_block_then_throws_PropertyNotFoundException() {
        Block block = mockBlock();

        when(propertyJpaRepository.findById(anyLong())).thenThrow(new PropertyNotFoundException(2L));

        assertThrows(PropertyNotFoundException.class, () -> blockDatasource.update(block));

        verify(propertyJpaRepository).findById(anyLong());
        verify(blockJpaRepository, never()).save(any(BlockEntity.class));
    }


    @Test
    void when_delete_block_then_return_success() {
        doNothing().when(blockJpaRepository).deleteById(anyLong());

        blockDatasource.delete(1L);

        verify(blockJpaRepository).deleteById(anyLong());
    }

    @Test
    void when_get_by_id_then_return_success() {
        BlockEntity blockEntity = mockBlockEntity();
        Block block = mockBlock();

        when(blockJpaRepository.findById(anyLong())).thenReturn(Optional.of(blockEntity));

        Block response = blockDatasource.get(1L);

        assertEquals(block, response);
        verify(blockJpaRepository).findById(anyLong());
    }

    @Test
    void when_get_by_id_then_throws_BlockNotFound() {
        when(blockJpaRepository.findById(anyLong())).thenThrow(new BlockNotFound(2L));

        assertThrows(BlockNotFound.class, () -> blockDatasource.get(2L));

        verify(blockJpaRepository).findById(anyLong());
    }

    @Test
    void when_list_blocks_by_property_then_return_success() {
        PropertyEntity propertyEntity = mockPropertyEntity();
        BlockEntity blockEntity = mockBlockEntity();

        when(propertyJpaRepository.findById(anyLong())).thenReturn(Optional.of(propertyEntity));
        when(blockJpaRepository.findByProperty(any(PropertyEntity.class))).thenReturn(List.of(blockEntity));

        List<Block> response = blockDatasource.listByProperty(1L);

        assertThat(response).hasSize(1);
        verify(propertyJpaRepository).findById(anyLong());
        verify(blockJpaRepository).findByProperty(any(PropertyEntity.class));
    }

    @Test
    void when_list_blocks_by_property_then_throws_PropertyNotFoundException() {
        when(propertyJpaRepository.findById(anyLong())).thenThrow(new PropertyNotFoundException(2L));

        assertThrows(PropertyNotFoundException.class, () -> blockDatasource.listByProperty(1L));

        verify(propertyJpaRepository).findById(anyLong());
        verify(blockJpaRepository, never()).findByProperty(any(PropertyEntity.class));
    }
}