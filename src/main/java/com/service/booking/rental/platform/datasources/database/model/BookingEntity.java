package com.service.booking.rental.platform.datasources.database.model;

import com.service.booking.rental.platform.entities.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "BOOKING")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "ID_GUEST")
    private PropertyUserEntity guest;

    @ManyToOne
    @JoinColumn(name = "ID_PROPERTY")
    private PropertyEntity property;

    @Column(name = "STATUS")
    @Enumerated(STRING)
    private BookingStatus status;

}
