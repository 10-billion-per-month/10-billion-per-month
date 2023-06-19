package com.example.dev.repository;

import com.example.dev.entity.Qrcode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrcodeRepository extends JpaRepository<Qrcode, Long> {

    /**
     * 가게의 큐알코드 목록 조회
     * @param storeId
     * @param pageable
     * @return
     */
    Page<Qrcode> findAllByStore_StoreId(Long storeId, Pageable pageable);
}
