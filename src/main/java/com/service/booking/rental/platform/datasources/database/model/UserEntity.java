package com.service.booking.rental.platform.datasources.database.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USERS")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_NAME", unique = true)
    private String username;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ACCOUNT_NON_EXPIRED")
    private Boolean accountNonExpired;

    @Column(name = "ACCOUNT_NON_LOCKED")
    private Boolean accountNonLocked;

    @Column(name = "CREDENTIALS_NON_EXPIRED")
    private Boolean credentialsNonExpired;

    @Column(name = "ENABLED")
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_PERMISSION",
            joinColumns = {@JoinColumn(name = "ID_USER")},
            inverseJoinColumns = {@JoinColumn(name = "ID_PERMISSION")}
    )
    private List<PermissionEntity> permissions;

    public List<String> getRoles(){
        List<String> roles = new ArrayList<>();

        for(PermissionEntity permission : permissions){
            roles.add(permission.getDescription());
        }

        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
