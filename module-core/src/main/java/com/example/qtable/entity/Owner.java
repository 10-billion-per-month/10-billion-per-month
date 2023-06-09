package com.example.qtable.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Owner extends AuditingFields {

    @Id
    private String ownerId;
    private String ownerPw;
    private String ownerName;
    private LocalDateTime ownerBirth;
    private String ownerStatus;

}
