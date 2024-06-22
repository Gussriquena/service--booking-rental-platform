package com.service.booking.rental.platform.entities;

import com.service.booking.rental.platform.entities.enums.BookingStatus;
import lombok.*;

import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Booking {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private PropertyUser user;
    private BookingStatus status;
}
