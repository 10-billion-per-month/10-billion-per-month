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
public class Menu extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    private String menuName;
    private Integer menuPrice;
    private String menuImage;
    private String menuStatus;
    private String menuBadge;
    private String menuDescription;

    @Builder
    public Menu(Store store, Category category, String menuName, Integer menuPrice, String menuImage, String menuStatus, String menuBadge, String menuDescription) {
        this.store = store;
        this.category = category;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuImage = menuImage;
        this.menuStatus = menuStatus;
        this.menuBadge = menuBadge;
        this.menuDescription = menuDescription;
    }
}
