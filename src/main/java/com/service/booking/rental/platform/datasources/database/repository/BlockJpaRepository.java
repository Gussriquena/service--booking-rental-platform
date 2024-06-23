package com.service.booking.rental.platform.datasources.database.repository;

import com.service.booking.rental.platform.datasources.database.model.BlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface BlockJpaRepository extends JpaRepository<BlockEntity, Long> {

    @Query("SELECT COUNT(b) > 0 FROM BlockEntity b " +
            "WHERE b.property.id =:idProperty " +
            "AND b.startDate >= :startDate AND b.endDate <= :endDate")
    boolean hasBlocksByPropertyWithinDate(
            @Param("idProperty") Long idProperty,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

}
