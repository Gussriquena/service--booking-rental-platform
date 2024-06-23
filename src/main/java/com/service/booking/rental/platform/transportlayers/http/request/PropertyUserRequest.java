package com.service.booking.rental.platform.transportlayers.http.request;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class PropertyUserRequest {
    private Long id;
    private String fullName;
    private String mail;
    private String role;
}
