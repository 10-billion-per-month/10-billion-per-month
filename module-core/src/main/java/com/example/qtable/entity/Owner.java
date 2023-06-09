package com.example.qtable.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Owner extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerId;
    private String ownerEmail;
    private String ownerPw;
    private String ownerName;
    private LocalDateTime ownerBirth;
    private String ownerStatus;

    @Builder
    public Owner(String ownerEmail, String ownerPw, String ownerName, LocalDateTime ownerBirth, String ownerStatus) {
        this.ownerEmail = ownerEmail;
        this.ownerPw = ownerPw;
        this.ownerName = ownerName;
        this.ownerBirth = ownerBirth;
        this.ownerStatus = ownerStatus;
    }
}
