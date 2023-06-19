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

    public boolean isEmpty() {
        boolean isEmpty = true;
        if (size != null) isEmpty = false;
        if (onColor != null) isEmpty = false;
        if (offColor != null) isEmpty = false;
        if (extension != null) isEmpty = false;
        return isEmpty;
    }

}
