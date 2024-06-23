package com.service.booking.rental.platform.entities;

import lombok.*;

import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Block {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Property property;
}
