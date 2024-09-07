package com.service.booking.rental.platform.transportlayers.controller;

import com.service.booking.rental.platform.entities.security.AccountCredentials;
import com.service.booking.rental.platform.interactors.service.AuthService;
import com.service.booking.rental.platform.transportlayers.http.request.AccountCredentialsRequest;
import com.service.booking.rental.platform.transportlayers.mappers.AccountCredentialsMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/auth")
public class AuthControllerImpl {

    private static final AccountCredentialsMapper MAPPER = AccountCredentialsMapper.INSTANCE;
    private final AuthService authService;

    public AuthControllerImpl(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody AccountCredentialsRequest request){
        if (validateCredentials(request)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        }

        AccountCredentials credentials = MAPPER.map(request);
        var token = authService.signin(credentials);

        if(isNull(token)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        }

        return token;
    }

    private boolean validateCredentials(AccountCredentialsRequest request){
        return isNull(request) ||
                isNull(request.getUsername()) ||
                request.getUsername().isBlank() ||
                isNull(request.getPassword()) ||
                request.getPassword().isBlank();
    }
}
