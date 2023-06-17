package com.example.dev.entity;

import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Where(clause = "delete_at = 'N'")
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
    public Menu(String delteAt, Store store, Category category, String menuName, Integer menuPrice, String menuImage, String menuStatus, String menuBadge, String menuDescription) {
        this.store = store;
        this.category = category;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuImage = menuImage;
        this.menuStatus = menuStatus;
        this.menuBadge = menuBadge;
        this.menuDescription = menuDescription;
        super.deleteAt = delteAt;
    }

    public void modifyMenu(Menu menu, boolean nullAble) {
        if (nullAble) {
            this.category = menu.getCategory();
            this.menuName = menu.getMenuName();
            this.menuPrice = menu.getMenuPrice();
            this.menuImage = menu.getMenuImage();
            this.menuStatus = menu.getMenuStatus();
            this.menuBadge = menu.getMenuBadge();
            this.menuDescription = menu.getMenuDescription();
        } else {
            this.category = menu.getCategory() != null ? menu.getCategory() : this.category;
            this.menuName = menu.getMenuName() != null ? menu.getMenuName() : this.menuName;
            this.menuPrice = menu.getMenuPrice() != null ? menu.getMenuPrice() : this.menuPrice;
            this.menuImage = menu.getMenuImage() != null ? menu.getMenuImage() : this.menuImage;
            this.menuStatus = menu.getMenuStatus() != null ? menu.getMenuStatus() : this.menuStatus;
            this.menuBadge = menu.getMenuBadge() != null ? menu.getMenuBadge() : this.menuBadge;
            this.menuDescription = menu.getMenuDescription() != null ? menu.getMenuDescription() : this.menuDescription;
            super.deleteAt = menu.getDeleteAt() != null ? menu.getDeleteAt() : super.deleteAt;
        }
    }
}
