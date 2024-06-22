package com.service.booking.rental.platform.transportlayers.mappers;

import com.service.booking.rental.platform.entities.Booking;
import com.service.booking.rental.platform.transportlayers.http.request.BookingCreateRequest;
import com.service.booking.rental.platform.transportlayers.http.request.BookingUpdateRequest;
import com.service.booking.rental.platform.transportlayers.http.response.BookingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @Mapping(target = "property.id", source = "idProperty")
    Booking map(BookingUpdateRequest request);

    BookingResponse map(Booking booking);

    @Mapping(target = "guest.id", source = "idGuest")
    @Mapping(target = "property.id", source = "idProperty")
    @Mapping(target = "status", expression = "java(BookingStatus.CONFIRMED)")
    Booking map(BookingCreateRequest request);
}
