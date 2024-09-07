package com.service.booking.rental.platform.transportlayers.http.request;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class AccountCredentialsRequest {
    private String username;
    private String password;
}
