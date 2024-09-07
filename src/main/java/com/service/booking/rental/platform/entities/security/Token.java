package com.service.booking.rental.platform.entities.security;

import lombok.*;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Token {
    private String username;
    private String token;
    private Boolean authenticated;
    private Date creation;
    private Date expiration;
    private String accessToken;
    private String refreshToken;
}
