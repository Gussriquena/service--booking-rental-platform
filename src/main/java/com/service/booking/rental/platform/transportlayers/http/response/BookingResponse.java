package com.service.booking.rental.platform.transportlayers.http.response;

import lombok.*;

import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BookingResponse {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private PropertyUserResponse guest;
    private PropertyResponse property;
}
