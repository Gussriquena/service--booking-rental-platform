package com.service.booking.rental.platform.util;


import com.service.booking.rental.platform.datasources.database.model.PropertyUserEntity;
import com.service.booking.rental.platform.entities.PropertyUser;
import com.service.booking.rental.platform.entities.enums.Role;

public class PropertyUserFactory {

    public static PropertyUser mockOwnerPropertyUser() {
        return PropertyUser.builder()
                .id(1L)
                .fullName("John Doe")
                .mail("john.doe@gmail.com")
                .role(Role.OWNER)
                .build();
    }

    public static PropertyUser mockGuestPropertyUser() {
        return PropertyUser.builder()
                .id(2L)
                .fullName("Mike Doe")
                .mail("mike.doe@gmail.com")
                .role(Role.GUEST)
                .build();
    }

    public static PropertyUserEntity mockOwnerPropertyUserEntity() {
        return PropertyUserEntity.builder()
                .id(1L)
                .fullName("John Doe")
                .mail("john.doe@gmail.com")
                .role(Role.OWNER)
                .build();
    }

    public static PropertyUserEntity mockGuestPropertyUserEntity() {
        return PropertyUserEntity.builder()
                .id(2L)
                .fullName("Mike Doe")
                .mail("mike.doe@gmail.com")
                .role(Role.GUEST)
                .build();
    }

}
