package com.example.qtable.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;
    private Long storeId;
    private Long categoryId;
    private String menuName;
    private Integer menuPrice;
    private String menuImage;
    private String menuStatus;
    private String menuBadge;
    private String menuDescription;

    @Builder
    public Menu(Long storeId, Long categoryId, String menuName, Integer menuPrice, String menuImage, String menuStatus, String menuBadge, String menuDescription) {
        this.storeId = storeId;
        this.categoryId = categoryId;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuImage = menuImage;
        this.menuStatus = menuStatus;
        this.menuBadge = menuBadge;
        this.menuDescription = menuDescription;
    }
}
