package com.service.booking.rental.platform.datasources.database.repository;

import com.service.booking.rental.platform.datasources.database.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.username =:userName")
    UserEntity findByUsername(@Param("userName") String username);

}
