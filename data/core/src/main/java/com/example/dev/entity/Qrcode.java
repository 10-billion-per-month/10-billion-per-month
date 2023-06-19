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
public class Qrcode extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qrcodeId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
    private String qrcodeImage;
    private String qrcodeName;

    @Builder
    public Qrcode(Store store, String qrcodeImage, String qrcodeName) {
        this.store = store;
        this.qrcodeImage = qrcodeImage;
        this.qrcodeName = qrcodeName;
    }

    public void modifyQrcode(Qrcode qrcode, boolean nullAble) {
        if (nullAble) {
            this.qrcodeImage = qrcode.getQrcodeImage();
            this.qrcodeName = qrcode.getQrcodeName();
        } else {
            this.qrcodeImage = qrcode.getQrcodeImage() != null ? qrcode.getQrcodeImage() : this.qrcodeImage;
            this.qrcodeName = qrcode.getQrcodeName() != null ? qrcode.getQrcodeName() : this.qrcodeName;
            super.deleteAt = qrcode.getDeleteAt() != null ? qrcode.getDeleteAt() : super.deleteAt;
        }
    }


}
