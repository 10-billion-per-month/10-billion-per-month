package com.example.dev.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class QrcodeImageConfig {

    private Integer size;
    private Integer onColor;
    private Integer offColor;
    private String extension;
}
