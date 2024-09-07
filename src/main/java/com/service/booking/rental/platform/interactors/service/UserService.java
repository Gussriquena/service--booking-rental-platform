package com.service.booking.rental.platform.interactors.service;

import com.service.booking.rental.platform.entities.User;
import com.service.booking.rental.platform.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Finding user by username {}", username);
        User user = repository.findByUsername(username);

        if(nonNull(user)){
            return user;
        }

        throw new UsernameNotFoundException("Username not found: " + username);
    }
}
