package com.service.booking.rental.platform.transportlayers.mappers;

import com.service.booking.rental.platform.entities.Block;
import com.service.booking.rental.platform.transportlayers.http.request.BlockRequest;
import com.service.booking.rental.platform.transportlayers.http.response.BlockResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BlockMapper {

    BlockMapper INSTANCE = Mappers.getMapper(BlockMapper.class);

    @Mapping(target = "property.id", source = "idProperty")
    Block map(BlockRequest request);

    BlockResponse map(Block block);
    List<BlockResponse> map(List<Block> block);
}
