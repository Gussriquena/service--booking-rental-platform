package com.service.booking.rental.platform.datasources.mapper;

import com.service.booking.rental.platform.datasources.database.model.BlockEntity;
import com.service.booking.rental.platform.entities.Block;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BlockMapper {

    BlockMapper INSTANCE = Mappers.getMapper(BlockMapper.class);

    BlockEntity map(Block block);

    Block map(BlockEntity entity);

    List<Block> map(List<BlockEntity> entity);
}
