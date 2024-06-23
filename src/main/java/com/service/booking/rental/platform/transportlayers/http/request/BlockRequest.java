package com.service.booking.rental.platform.transportlayers.http.request;

import lombok.*;

import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BlockRequest {
    private Long idProperty;
    private LocalDate startDate;
    private LocalDate endDate;
}
