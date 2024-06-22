package com.service.booking.rental.platform.datasources;

import com.service.booking.rental.platform.datasources.database.model.BookingEntity;
import com.service.booking.rental.platform.datasources.database.repository.BookingJpaRepository;
import com.service.booking.rental.platform.datasources.database.repository.PropertyUserJpaRepository;
import com.service.booking.rental.platform.datasources.mapper.BookingMapper;
import com.service.booking.rental.platform.entities.Booking;
import com.service.booking.rental.platform.exceptions.BookingNotFoundException;
import com.service.booking.rental.platform.exceptions.UserNotFoundException;
import com.service.booking.rental.platform.repositores.BookingRepository;
import org.springframework.stereotype.Component;

@Component
public class BookingDatasource implements BookingRepository {
    private static final BookingMapper MAPPER = BookingMapper.INSTANCE;
    private final BookingJpaRepository bookingJpaRepository;
    private final PropertyUserJpaRepository propertyUserJpaRepository;

    public BookingDatasource(BookingJpaRepository bookingJpaRepository, PropertyUserJpaRepository propertyUserJpaRepository){
        this.bookingJpaRepository = bookingJpaRepository;
        this.propertyUserJpaRepository = propertyUserJpaRepository;
    }

    public Booking getBookingById(Long id) {
        BookingEntity entity = bookingJpaRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));

        return MAPPER.map(entity);
    }

    public Booking updateBookingById(Long id, Booking booking) {
        BookingEntity entity = MAPPER.map(id, booking);

        if(bookingJpaRepository.findById(id).isEmpty()){
            throw new BookingNotFoundException(id);
        }

        if(propertyUserJpaRepository.findById(entity.getUser().getId()).isEmpty()){
            throw new UserNotFoundException(entity.getUser().getId());
        }

        propertyUserJpaRepository.save(entity.getUser());
        BookingEntity updatedEntity = bookingJpaRepository.save(entity);

        return MAPPER.map(updatedEntity);
    }

    public void deleteById(Long id) {
        bookingJpaRepository.deleteById(id);
    }
}
