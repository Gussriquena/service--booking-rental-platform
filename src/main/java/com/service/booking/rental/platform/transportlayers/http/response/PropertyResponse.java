package com.service.booking.rental.platform.transportlayers.http.response;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PropertyResponse {
    private Long id;
    private String name;
    private String address;
    private PropertyUserResponse owner;
}
