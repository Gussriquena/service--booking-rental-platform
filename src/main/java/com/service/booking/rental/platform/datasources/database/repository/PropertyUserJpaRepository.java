package com.service.booking.rental.platform.datasources.database.repository;

import com.service.booking.rental.platform.datasources.database.model.PropertyUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyUserJpaRepository extends JpaRepository<PropertyUserEntity, Long> {
}
