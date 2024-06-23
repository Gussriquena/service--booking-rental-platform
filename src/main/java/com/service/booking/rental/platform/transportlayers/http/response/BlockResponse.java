package com.service.booking.rental.platform.transportlayers.http.response;

import lombok.*;

import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class BlockResponse {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private PropertyResponse property;
}
