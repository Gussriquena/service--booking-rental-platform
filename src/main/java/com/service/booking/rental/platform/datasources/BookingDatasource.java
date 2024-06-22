package com.service.booking.rental.platform.datasources;

import com.service.booking.rental.platform.datasources.database.model.BookingEntity;
import com.service.booking.rental.platform.datasources.database.model.PropertyEntity;
import com.service.booking.rental.platform.datasources.database.model.PropertyUserEntity;
import com.service.booking.rental.platform.datasources.database.repository.BookingJpaRepository;
import com.service.booking.rental.platform.datasources.database.repository.PropertyJpaRepository;
import com.service.booking.rental.platform.datasources.database.repository.PropertyUserJpaRepository;
import com.service.booking.rental.platform.datasources.mapper.BookingMapper;
import com.service.booking.rental.platform.entities.Booking;
import com.service.booking.rental.platform.exceptions.BookingNotFoundException;
import com.service.booking.rental.platform.exceptions.PropertyNotFoundException;
import com.service.booking.rental.platform.exceptions.UserNotFoundException;
import com.service.booking.rental.platform.repositores.BookingRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class BookingDatasource implements BookingRepository {
    private static final BookingMapper MAPPER = BookingMapper.INSTANCE;
    private final BookingJpaRepository bookingJpaRepository;
    private final PropertyUserJpaRepository propertyUserJpaRepository;
    private final PropertyJpaRepository propertyJpaRepository;

    public BookingDatasource(BookingJpaRepository bookingJpaRepository,
                             PropertyUserJpaRepository propertyUserJpaRepository,
                             PropertyJpaRepository propertyJpaRepository){
        this.bookingJpaRepository = bookingJpaRepository;
        this.propertyUserJpaRepository = propertyUserJpaRepository;
        this.propertyJpaRepository = propertyJpaRepository;
    }

    @Transactional
    public Booking bookProperty(Booking booking) {
        BookingEntity entity = MAPPER.map(booking);

        Long guestId = entity.getGuest().getId();
        Long propertyId = entity.getProperty().getId();

        PropertyUserEntity guestEntity = propertyUserJpaRepository.findById(guestId)
                .orElseThrow(() -> new UserNotFoundException(guestId));

        PropertyEntity propertyEntity = propertyJpaRepository.findById(propertyId)
                .orElseThrow(() -> new PropertyNotFoundException(propertyId));

        BookingEntity createdEntity = bookingJpaRepository.save(entity);

        createdEntity.setGuest(guestEntity);
        createdEntity.setProperty(propertyEntity);

        return MAPPER.map(createdEntity);
    }

    public Booking getBookingById(Long id) {
        BookingEntity entity = bookingJpaRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));

        return MAPPER.map(entity);
    }

    @Transactional
    public Booking updateBookingById(Long id, Booking booking) {
        BookingEntity entity = MAPPER.map(id, booking);

        if(bookingJpaRepository.findById(id).isEmpty()){
            throw new BookingNotFoundException(id);
        }

        if(propertyUserJpaRepository.findById(entity.getGuest().getId()).isEmpty()){
            throw new UserNotFoundException(entity.getGuest().getId());
        }

        if(propertyJpaRepository.findById(entity.getProperty().getId()).isEmpty()){
            throw new PropertyNotFoundException(entity.getProperty().getId());
        }

        propertyUserJpaRepository.save(entity.getGuest());
        BookingEntity updatedEntity = bookingJpaRepository.save(entity);

        return MAPPER.map(updatedEntity);
    }

    public void deleteById(Long id) {
        bookingJpaRepository.deleteById(id);
    }
}
