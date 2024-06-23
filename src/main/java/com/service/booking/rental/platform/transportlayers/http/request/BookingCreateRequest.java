package com.service.booking.rental.platform.transportlayers.http.request;

import lombok.*;

import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class BookingCreateRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private Long idGuest;
    private Long idProperty;
}
