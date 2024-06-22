package com.service.booking.rental.platform.datasources.mapper;

import com.service.booking.rental.platform.datasources.database.model.BookingEntity;
import com.service.booking.rental.platform.entities.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    Booking map(BookingEntity bookingEntity);

    @Mapping(target = "id", source = "id")
    BookingEntity map(Long id, Booking booking);
}
