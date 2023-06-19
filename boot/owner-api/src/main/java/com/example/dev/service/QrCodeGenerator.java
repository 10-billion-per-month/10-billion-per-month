package com.example.dev.service;

import com.example.dev.dto.QrcodeImageConfig;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QrCodeGenerator {

//    @Value("${path.image.qrCode}")
//    private String filePathPrefix;

    static String generateQrCodeImage(String text, String filePath, QrcodeImageConfig imageConfig)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, imageConfig.getSize(), imageConfig.getSize());

        String fullPath = filePath + "." + imageConfig.getExtension();
        File file = new File(fullPath);
        if(!file.exists()){
            file.mkdirs();
        }

//        int qrColor = 0xFFad1004;
        int qrColor = 0xffed8878;
        int black = 0xff111111;
        int white = 0xffffffff;
        MatrixToImageConfig matrixToImageConfig;
        if(imageConfig.getOnColor() == null || imageConfig.getOffColor() == null) {
            matrixToImageConfig = new MatrixToImageConfig(black, white);
        } else {
            matrixToImageConfig = new MatrixToImageConfig(imageConfig.getOnColor(), imageConfig.getOffColor());
        }

        Path path = FileSystems.getDefault().getPath(fullPath);
        MatrixToImageWriter.writeToPath(bitMatrix, imageConfig.getExtension(), path, matrixToImageConfig);

        return fullPath;
    }
}
