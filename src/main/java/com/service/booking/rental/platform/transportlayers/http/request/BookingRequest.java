package com.service.booking.rental.platform.transportlayers.http.request;

import lombok.*;

import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BookingRequest {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private PropertyUserRequest user;
    private String status;
}
