package com.example.dev.entity;

import jakarta.persistence.*;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
    private String categoryName;
    private String categoryDescription;

    @Builder
    public Category(String deleteAt, Store store, String categoryName, String categoryDescription) {
        super.deleteAt = deleteAt;
        this.store = store;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public void modifyCategory(Category category, boolean nullAble) {
        if (nullAble) {
            this.categoryName = category.getCategoryName();
            this.categoryDescription = category.getCategoryDescription();
        } else {
            this.categoryName = category.getCategoryName() != null ? category.getCategoryName() : this.categoryName;
            this.categoryDescription = category.getCategoryDescription() != null ? category.getCategoryDescription() : this.categoryDescription;
            super.deleteAt = category.getDeleteAt() != null ? category.getDeleteAt() : super.deleteAt;
        }
    }
}
