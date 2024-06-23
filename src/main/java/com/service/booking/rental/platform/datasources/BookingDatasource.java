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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Slf4j
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

    public Booking bookProperty(Booking booking) {
        log.info("BookingDatasource - creating new booking on database");

        BookingEntity entity = MAPPER.map(booking);

        Long idGuest = entity.getGuest().getId();
        Long idProperty = entity.getProperty().getId();

        PropertyUserEntity guestEntity = propertyUserJpaRepository.findById(idGuest)
                .orElseThrow(() -> new UserNotFoundException(idGuest));

        PropertyEntity propertyEntity = propertyJpaRepository.findById(idProperty)
                .orElseThrow(() -> new PropertyNotFoundException(idProperty));

        BookingEntity createdEntity = bookingJpaRepository.save(entity);

        createdEntity.setGuest(guestEntity);
        createdEntity.setProperty(propertyEntity);

        return MAPPER.map(createdEntity);
    }

    public Booking getBookingById(Long id) {
        log.info("BookingDatasource - retrieving booking by id from database");
        BookingEntity entity = bookingJpaRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));

        return MAPPER.map(entity);
    }

    public Booking updateBookingById(Long id, Booking booking) {
        log.info("BookingDatasource - updating booking on database by id");

        BookingEntity entity = MAPPER.map(id, booking);

        Long idProperty = entity.getProperty().getId();
        Long idPropertyUser = entity.getGuest().getId();

        if(bookingJpaRepository.findById(id).isEmpty()){
            throw new BookingNotFoundException(id);
        }

        if(propertyUserJpaRepository.findById(idPropertyUser).isEmpty()){
            throw new UserNotFoundException(idPropertyUser);
        }

        if(propertyJpaRepository.findById(idProperty).isEmpty()){
            throw new PropertyNotFoundException(idProperty);
        }

        propertyUserJpaRepository.save(entity.getGuest());
        BookingEntity updatedEntity = bookingJpaRepository.save(entity);

        return MAPPER.map(updatedEntity);
    }

    public void deleteById(Long id) {
        log.info("BookingDatasource - deleting booking from database");
        bookingJpaRepository.deleteById(id);
    }

    public boolean hasOverlapingDates(Long idProperty, LocalDate startDate, LocalDate endDate) {
        log.info("BookingDatasource - SQL - checking for overlaping dates with parameters - [idProperty, startDate, endDate]");
        return bookingJpaRepository.hasOverlapingDates(idProperty, startDate, endDate);
    }

    public boolean hasOverlapingDatesForGuest(Booking booking) {
        log.info("BookingDatasource - SQL - checking for overlaping dates with guest id");
        return bookingJpaRepository.hasOverlapingDatesForGuest(
                booking.getProperty().getId(),
                booking.getGuest().getId(),
                booking.getStartDate(),
                booking.getEndDate());
    }

    public void updateStatus(Booking booking) {
        log.info("BookingDatasource - updating booking data on database");
        BookingEntity entity = MAPPER.map(booking);
        bookingJpaRepository.save(entity);
    }
}
