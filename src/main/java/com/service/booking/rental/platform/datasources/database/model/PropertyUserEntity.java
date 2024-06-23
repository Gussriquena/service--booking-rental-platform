package com.service.booking.rental.platform.datasources.database.model;

import com.service.booking.rental.platform.entities.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PROPERTY_USER")
public class PropertyUserEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "MAIL")
    private String mail;

    @Column(name = "ROLE")
    @Enumerated(STRING)
    private Role role;

}
