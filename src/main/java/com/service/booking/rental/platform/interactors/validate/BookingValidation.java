package com.service.booking.rental.platform.interactors.validate;

import com.service.booking.rental.platform.entities.Block;
import com.service.booking.rental.platform.entities.Booking;
import com.service.booking.rental.platform.exceptions.InvalidDateException;
import com.service.booking.rental.platform.exceptions.PropertyUnavailableException;
import com.service.booking.rental.platform.repositories.BlockRepository;
import com.service.booking.rental.platform.repositories.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static java.util.Objects.isNull;

@Service
@Slf4j
public class BookingValidation {

    private final BookingRepository bookingRepository;
    private final BlockRepository blockRepository;

    public BookingValidation(BookingRepository bookingRepository, BlockRepository blockRepository){
        this.bookingRepository = bookingRepository;
        this.blockRepository = blockRepository;
    }

    public void validateBookingAvailability(Booking booking){
        log.info("BookingValidation - starting booking availability validation");

        validateDates(booking.getStartDate(), booking.getEndDate());
        validatePropertyOverlaps(booking);
        validateBlocksOnProperty(booking.getProperty().getId(), booking.getStartDate(), booking.getEndDate());
    }

    public void validateBookingUpdate(Booking booking){
        log.info("BookingValidation - starting booking validation for update");

        validateDates(booking.getStartDate(), booking.getEndDate());
        validatePropertyOverlapsForUpdating(booking);
        validateBlocksOnProperty(booking.getProperty().getId(), booking.getStartDate(), booking.getEndDate());
    }

    public void validateBlockFeasibility(Block block){
        log.info("BookingValidation - starting validation for placing block");

        validateDates(block.getStartDate(), block.getEndDate());
        validateBlocksOnProperty(block.getProperty().getId(), block.getStartDate(), block.getEndDate());
        validatePropertyOverlaps(block);
    }

    public void validateBlockUpdateFeasibility(Block block){
        log.info("BookingValidation - starting validation for update block");

        validateDates(block.getStartDate(), block.getEndDate());
        validatePropertyOverlaps(block);
    }

    private void validateDates(LocalDate startDate, LocalDate endDate){
        if(isNull(startDate) || isNull(endDate)){
            throw new InvalidDateException("Dates can't be null or empty");
        }

        if(startDate.isBefore(LocalDate.now())){
            throw new InvalidDateException("Dates before current date can't be booked");
        }
    }

    private void validatePropertyOverlaps(Block block){
        boolean hasOverlapingDates = bookingRepository.hasOverlapingDates(block.getProperty().getId(), block.getStartDate(), block.getEndDate());

        if (hasOverlapingDates){
            throw new PropertyUnavailableException("Error placing block: the chosen period is already booked");
        }
    }

    private void validatePropertyOverlaps(Booking booking){
        boolean hasOverlapingDates = bookingRepository.hasOverlapingDates(booking.getProperty().getId(), booking.getStartDate(), booking.getEndDate());

        if (hasOverlapingDates){
            throw new PropertyUnavailableException("The chosen period is unavailable for this property");
        }
    }

    private void validatePropertyOverlapsForUpdating(Booking booking){
        boolean hasOverlapingDates = bookingRepository.hasOverlapingDatesForGuest(booking);

        if (hasOverlapingDates){
            throw new PropertyUnavailableException("The chosen period is unavailable for this property");
        }
    }

    private void validateBlocksOnProperty(Long idProperty, LocalDate startDate, LocalDate endDate){
        boolean hasBlocks = blockRepository.hasBlocksByPropertyWithinDate(idProperty, startDate, endDate);

        if(hasBlocks){
            throw new PropertyUnavailableException("The property is blocked within selected dates");
        }
    }
}
