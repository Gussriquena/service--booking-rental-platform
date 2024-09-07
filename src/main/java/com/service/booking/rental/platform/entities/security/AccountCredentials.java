package com.service.booking.rental.platform.entities.security;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class AccountCredentials {
    private String username;
    private String password;
}
