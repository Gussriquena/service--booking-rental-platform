package com.service.booking.rental.platform.util;

import com.service.booking.rental.platform.datasources.database.model.PropertyEntity;
import com.service.booking.rental.platform.entities.Property;
import com.service.booking.rental.platform.transportlayers.http.response.PropertyResponse;
import com.service.booking.rental.platform.transportlayers.http.response.PropertyUserResponse;

import static com.service.booking.rental.platform.util.PropertyUserFactory.*;

public class PropertyFactory {

    public static Property mockProperty(){
        return Property.builder()
                .id(1L)
                .name("Village tiny house")
                .address("7st, nowhere")
                .owner(mockOwnerPropertyUser())
                .build();
    }

    public static PropertyEntity mockPropertyEntity() {
        return PropertyEntity.builder()
                .id(1L)
                .name("Village tiny house")
                .address("7st, nowhere")
                .owner(mockOwnerPropertyUserEntity())
                .build();
    }

    public static PropertyResponse mockPropertyResponse() {
        return PropertyResponse.builder()
                .id(1L)
                .name("Village tiny house")
                .address("7st, nowhere")
                .owner(mockOwnerPropertyUserResponse())
                .build();
    }
}
