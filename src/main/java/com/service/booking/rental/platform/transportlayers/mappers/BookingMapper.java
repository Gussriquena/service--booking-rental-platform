package com.service.booking.rental.platform.transportlayers.mappers;

import com.service.booking.rental.platform.entities.Booking;
import com.service.booking.rental.platform.transportlayers.http.request.BookingRequest;
import com.service.booking.rental.platform.transportlayers.http.response.BookingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    Booking map(BookingRequest request);
    BookingResponse map(Booking booking);

}
