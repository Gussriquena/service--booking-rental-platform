package com.service.booking.rental.platform.interactors.usecase;

import com.service.booking.rental.platform.entities.Booking;
import com.service.booking.rental.platform.exceptions.InvalidDateException;
import com.service.booking.rental.platform.exceptions.PropertyUnavailableException;
import com.service.booking.rental.platform.repositores.BlockRepository;
import com.service.booking.rental.platform.repositores.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static java.util.Objects.isNull;

@Service
public class BookPropertyUseCase {

    private final BookingRepository bookingRepository;
    private final BlockRepository blockRepository;

    public BookPropertyUseCase(BookingRepository bookingRepository, BlockRepository blockRepository){
        this.bookingRepository = bookingRepository;
        this.blockRepository = blockRepository;
    }

    public Booking execute(Booking booking){
        validateDates(booking.getStartDate(), booking.getEndDate());
        validatePropertyOverlaps(booking);
        validateBlocksOnProperty(booking);

        return bookingRepository.bookProperty(booking);
    }

    private void validatePropertyOverlaps(Booking booking){
        boolean hasOverlapingDates = bookingRepository.hasOverlapingDates(booking.getProperty().getId(), booking.getStartDate(), booking.getEndDate());

        if (hasOverlapingDates){
            throw new PropertyUnavailableException("The chosen period is unavailable for this property");
        }
    }

    private void validateBlocksOnProperty(Booking booking){
        boolean hasBlocks = blockRepository.hasBlocksByPropertyWithinDate(booking.getProperty().getId(), booking.getStartDate(), booking.getEndDate());

        if(hasBlocks){
            throw new PropertyUnavailableException("The property is blocked within selected dates");
        }
    }

    private void validateDates(LocalDate startDate, LocalDate endDate){
        if(isNull(startDate) || isNull(endDate)){
            throw new InvalidDateException("Dates can't be null or empty");
        }

        if(startDate.isBefore(LocalDate.now())){
            throw new InvalidDateException("Dates before current date can't be booked");
        }
    }

}
