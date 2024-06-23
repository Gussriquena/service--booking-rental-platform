package com.service.booking.rental.platform.transportlayers.http.request;

import lombok.*;

import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class BookingUpdateRequest {
    private Long id;
    private Long idProperty;
    private LocalDate startDate;
    private LocalDate endDate;
    private PropertyUserRequest guest;
    private String status;
}
