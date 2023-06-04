package com.example.qtable.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private Long storeId;
    private String categoryName;
    private String categoryDescription;

    @Builder
    public Category(Long storeId, String categoryName, String categoryDescription) {
        this.storeId = storeId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }
}
