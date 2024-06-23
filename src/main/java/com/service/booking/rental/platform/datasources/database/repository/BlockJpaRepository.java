package com.service.booking.rental.platform.datasources.database.repository;

import com.service.booking.rental.platform.datasources.database.model.BlockEntity;
import com.service.booking.rental.platform.datasources.database.model.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalDate;

public interface BlockJpaRepository extends JpaRepository<BlockEntity, Long> {

    @Transactional(readOnly=true)
    @Query(value = "SELECT COUNT(*) > 0 FROM BLOCK " +
            "WHERE ID_PROPERTY = :idProperty " +
            "AND (:startDate <= END_DATE AND :endDate >= START_DATE)", nativeQuery = true)
    boolean hasBlocksByPropertyWithinDate(
            @Param("idProperty") Long idProperty,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    List<BlockEntity> findByProperty(PropertyEntity idProperty);
}
