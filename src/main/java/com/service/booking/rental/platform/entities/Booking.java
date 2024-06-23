package com.service.booking.rental.platform.entities;

import com.service.booking.rental.platform.entities.enums.BookingStatus;
import lombok.*;

import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Booking {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private PropertyUser guest;
    private Property property;
    private BookingStatus status;
}
