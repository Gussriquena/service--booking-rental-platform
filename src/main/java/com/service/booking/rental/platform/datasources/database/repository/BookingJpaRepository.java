package com.service.booking.rental.platform.datasources.database.repository;

import com.service.booking.rental.platform.datasources.database.model.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface BookingJpaRepository extends JpaRepository<BookingEntity, Long> {

    @Transactional(readOnly=true)
    @Query(value = "SELECT COUNT(*) > 0 FROM BOOKING " +
            "WHERE ID_PROPERTY = :idProperty " +
            "AND STATUS = 'CONFIRMED' " +
            "AND (:startDate <= END_DATE AND :endDate >= START_DATE);", nativeQuery = true)
    boolean hasOverlapingDates(
            @Param("idProperty") Long idProperty,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Transactional(readOnly=true)
    @Query(value = "SELECT COUNT(*) > 0 FROM BOOKING " +
            "WHERE ID_PROPERTY = :idProperty " +
            "AND ID_GUEST != :idGuest " +
            "AND STATUS = 'CONFIRMED' " +
            "AND (:startDate <= END_DATE AND :endDate >= START_DATE);", nativeQuery = true)
    boolean hasOverlapingDatesForGuest(
            @Param("idProperty") Long idProperty,
            @Param("idGuest") Long idGuest,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

}
