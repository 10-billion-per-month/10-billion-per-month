package com.example.dev.service;

import com.example.dev.dto.QrcodeDto;
import com.example.dev.dto.QrcodeImageConfig;
import com.example.dev.entity.Category;
import com.example.dev.entity.Owner;
import com.example.dev.entity.Qrcode;
import com.example.dev.entity.Store;
import com.example.dev.repository.*;
import com.google.zxing.WriterException;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class QrcodeWriteServiceTest {

    @Autowired
    QrcodeWriteService qrcodeWriteService;
    @Autowired
    QrcodeRepository qrcodeRepository;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    DatabaseCleanup databaseCleanup;

    Owner owner;
    Store store;
    Category category1;
    Category category2;

    @BeforeEach
    void setUp() {
        databaseCleanup.execute();
        owner = ownerRepository.saveAndFlush(Owner.builder()
                .ownerName("정가영")
                .ownerBirth(LocalDateTime.now())
                .ownerEmail("wjdrkdudwkd@email.com")
                .ownerPw("rkdud123!")
                .build()
        );
        store = storeRepository.saveAndFlush(Store.builder()
                .storeName("가게1")
                .owner(owner)
                .build()
        );
        category1 = categoryRepository.saveAndFlush(
                Category.builder()
                        .store(store)
                        .categoryName(String.valueOf(Math.random()))
                        .categoryDescription(String.valueOf(Math.random()))
                        .build()
        );
        category2 = categoryRepository.saveAndFlush(
                Category.builder()
                        .store(store)
                        .categoryName(String.valueOf(Math.random()))
                        .categoryDescription(String.valueOf(Math.random()))
                        .build()
        );
    }

    public QrcodeDto createQrcodeDto() {
        return QrcodeDto.builder()
                .storeId(store.getStoreId())
                .qrcodeName(String.valueOf(Math.random()*100))
                .qrcodeImageConfig(QrcodeImageConfig.builder()
                        .size(300)
                        .onColor(0xffed8878)
                        .offColor(0xff111111)
                        .extension("png")
                        .build())
                .build();
    }

    public Qrcode saveQrcode(QrcodeDto dto) {
        return qrcodeRepository.saveAndFlush(dto.toEntity(store));
    }

    @Test
    @DisplayName("큐알코드 이미지를 생성한다.")
    void createQrcodeImage() {

        QrcodeImageConfig imageConfig = QrcodeImageConfig.builder()
                .onColor(0xffed8878)
                .offColor(0xff111111)
                .size(500)
                .extension("png")
                .build();

        String format = "png";
        try {
            QrCodeGenerator.generateQrCodeImage("1", "./img/owner/store/qrcode/1", imageConfig);
        } catch (WriterException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("큐알코드를 생성한다.")
    void createQrcode() {
        // given : 무엇을 할것인가? 데이터 세팅
        QrcodeDto qrcodeDto = createQrcodeDto();

        // when : 실제 수행
        QrcodeDto qrcode = qrcodeWriteService.createQrcode(qrcodeDto);

        // then : 수행 결과 확인
        List<Qrcode> qrcodes = qrcodeRepository.findAll();
        Assertions.assertThat(qrcode.getQrcodeName())
                .isEqualTo(qrcodeDto.getQrcodeName());

        Assertions.assertThat(qrcodes)
                .hasSize(1)
                .extracting("qrcodeName", "qrcodeId")
                .contains(new Tuple(qrcodes.get(0).getQrcodeName(), qrcodes.get(0).getQrcodeId()));
    }

    @Test
    @DisplayName("큐알코드를 수정한다")
    void modifyQrcode() {
        // given
        Qrcode qrcode = saveQrcode(createQrcodeDto());

        // when
        QrcodeDto qrcodeDto = QrcodeDto.builder()
                .qrcodeId(qrcode.getQrcodeId())
                .qrcodeName("qrcode 이름 변경")
                .build();
        qrcodeWriteService.modifyQrcode(qrcodeDto);

        // then
        Qrcode changeQrcode = qrcodeRepository.findById(qrcode.getQrcodeId()).get();
        Assertions.assertThat(changeQrcode.getQrcodeName())
                .isEqualTo("qrcode 이름 변경");
    }

    @Test
    @DisplayName("큐알코드 이미지를 수정한다")
    void modifyQrcodeImage() {
        // given
//        Qrcode qrcode = saveQrcode(createQrcodeDto());
        QrcodeDto qrcode = qrcodeWriteService.createQrcode(createQrcodeDto());

        // when
        QrcodeDto qrcodeDto = QrcodeDto.builder()
                .qrcodeId(qrcode.getQrcodeId())
                .qrcodeImageConfig(QrcodeImageConfig.builder().extension("png").offColor(0xffed8878).onColor(0xff111111).size(200).build())
                .build();
        qrcodeWriteService.modifyQrcode(qrcodeDto);

        // then
        Qrcode changeQrcode = qrcodeRepository.findById(qrcode.getQrcodeId()).get();
//        Assertions.assertThat(changeQrcode.getQrcodeName())
//                .isEqualTo("qrcode 이름 변경");
    }


}
