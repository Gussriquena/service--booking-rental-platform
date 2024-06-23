package com.service.booking.rental.platform.util;

import com.service.booking.rental.platform.datasources.database.model.PropertyEntity;
import com.service.booking.rental.platform.entities.Property;

import static com.service.booking.rental.platform.util.PropertyUserFactory.mockOwnerPropertyUser;
import static com.service.booking.rental.platform.util.PropertyUserFactory.mockOwnerPropertyUserEntity;

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

}
