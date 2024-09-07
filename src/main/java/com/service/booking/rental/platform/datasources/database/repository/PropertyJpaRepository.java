package com.service.booking.rental.platform.datasources.database.repository;

import com.service.booking.rental.platform.datasources.database.model.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyJpaRepository extends JpaRepository<PropertyEntity, Long> {
}
