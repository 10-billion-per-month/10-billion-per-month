package com.example.dev.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
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
