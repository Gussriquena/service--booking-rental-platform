package com.service.booking.rental.platform.util;

import com.service.booking.rental.platform.datasources.database.model.BlockEntity;
import com.service.booking.rental.platform.entities.Block;
import com.service.booking.rental.platform.transportlayers.http.request.BlockRequest;
import com.service.booking.rental.platform.transportlayers.http.response.BlockResponse;

import static com.service.booking.rental.platform.util.Constants.END_DATE;
import static com.service.booking.rental.platform.util.Constants.START_DATE;
import static com.service.booking.rental.platform.util.PropertyFactory.*;

public class BlockFactory {

    public static Block mockBlock(){
        return Block.builder()
                .id(1L)
                .startDate(START_DATE)
                .endDate(END_DATE)
                .property(mockProperty())
                .build();
    }

    public static BlockEntity mockBlockEntity(){
        return BlockEntity.builder()
                .id(1L)
                .startDate(START_DATE)
                .endDate(END_DATE)
                .property(mockPropertyEntity())
                .build();
    }

    public static BlockRequest mockBlockRequest(){
        return BlockRequest.builder()
                .idProperty(1L)
                .startDate(START_DATE)
                .endDate(END_DATE)
                .build();
    }

    public static BlockResponse mockBlockResponse(){
        return BlockResponse.builder()
                .id(1L)
                .startDate(START_DATE)
                .endDate(END_DATE)
                .property(mockPropertyResponse())
                .build();
    }
}
