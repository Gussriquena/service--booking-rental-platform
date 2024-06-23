package com.service.booking.rental.platform.entities;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Property {
    private Long id;
    private String name;
    private String address;
    private PropertyUser owner;
}
