package com.service.booking.rental.platform.datasources.mapper;

import com.service.booking.rental.platform.datasources.database.model.UserEntity;
import com.service.booking.rental.platform.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User map(UserEntity userEntity);
}
