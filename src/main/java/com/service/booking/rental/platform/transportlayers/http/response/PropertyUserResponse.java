package com.service.booking.rental.platform.transportlayers.http.response;

import com.service.booking.rental.platform.entities.enums.Role;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class PropertyUserResponse {
    private Long id;
    private String fullName;
    private String mail;
    private Role role;
}
