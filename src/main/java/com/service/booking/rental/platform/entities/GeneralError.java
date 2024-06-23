package com.service.booking.rental.platform.entities;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class GeneralError {
    private Integer code;
    private String message;
}
