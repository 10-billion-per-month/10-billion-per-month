package com.example.qtable.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Owner extends AuditingFields {
    @Id
    private Long ownerId;
    private String ownerPw;
    private String ownerName;
    private LocalDateTime ownerBirth;
    private String ownerStatus;
}
