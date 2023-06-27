package com.example.dev.dto;

import com.example.dev.entity.Owner;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
public class OwnerDto implements UserDetails {

    private Long ownerId;
    private String ownerEmail;
    private String ownerPw;
    private String ownerName;
    private LocalDateTime ownerBirth;
    private String ownerStatus;

    @Builder
    public OwnerDto(Long ownerId, String ownerEmail, String ownerPw, String ownerName, LocalDateTime ownerBirth, String ownerStatus) {
        this.ownerId = ownerId;
        this.ownerEmail = ownerEmail;
        this.ownerPw = ownerPw;
        this.ownerName = ownerName;
        this.ownerBirth = ownerBirth;
        this.ownerStatus = ownerStatus;
    }

    public Owner toEntity() {
        return Owner.builder()
                .ownerEmail(ownerEmail)
                .ownerPw(ownerPw)
                .ownerName(ownerName)
                .ownerBirth(ownerBirth)
                .ownerStatus(ownerStatus)
                .build();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return ownerPw;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}


