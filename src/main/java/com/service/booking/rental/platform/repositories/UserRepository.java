package com.service.booking.rental.platform.repositories;

import com.service.booking.rental.platform.entities.User;

public interface UserRepository {

    User findByUsername(String username);

}
