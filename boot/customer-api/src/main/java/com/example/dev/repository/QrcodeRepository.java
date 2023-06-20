package com.example.dev.repository;

import com.example.dev.entity.Qrcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QrcodeRepository extends JpaRepository<Qrcode, Long> {
}
