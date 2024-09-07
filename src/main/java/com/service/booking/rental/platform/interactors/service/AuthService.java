package com.service.booking.rental.platform.interactors.service;

import com.service.booking.rental.platform.entities.security.AccountCredentials;
import com.service.booking.rental.platform.entities.security.Token;
import com.service.booking.rental.platform.repositories.UserRepository;
import com.service.booking.rental.platform.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class AuthService {

    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthService(JwtTokenProvider tokenProvider, AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public ResponseEntity signin(AccountCredentials credentials){
        try {
            String username = credentials.getUsername();
            String password = credentials.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = userRepository.findByUsername(username);
            Token tokenResponse = new Token();

            if (nonNull(user)){
                tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
            } else{
                throw new UsernameNotFoundException("Username not found");
            }

            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e){
            throw new BadCredentialsException("Invalid username/password");
        }
    }
}
