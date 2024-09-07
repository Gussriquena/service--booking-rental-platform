package com.service.booking.rental.platform.datasources;

import com.service.booking.rental.platform.datasources.database.model.UserEntity;
import com.service.booking.rental.platform.datasources.database.repository.UserJpaRepository;
import com.service.booking.rental.platform.datasources.mapper.UserMapper;
import com.service.booking.rental.platform.entities.User;
import com.service.booking.rental.platform.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserDatasource implements UserRepository {

    private static final UserMapper MAPPER = UserMapper.INSTANCE;
    private final UserJpaRepository userJpaRepository;

    public UserDatasource(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User findByUsername(String username) {
        UserEntity entity = userJpaRepository.findByUsername(username);
        return MAPPER.map(entity);
    }
}
