package com.example.dev.controller;

import com.example.dev.dto.request.CreateCategoryRequestDto;
import com.example.dev.dto.request.CreateStoreRequestDto;
import com.example.dev.service.CategoryWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryWriteService categoryWriteService;

    /**
     * 카테고리 등록
     * @param requestDto
     */
    @PostMapping("/v1/category")
    public void createCategory(@RequestBody CreateCategoryRequestDto requestDto) {
        categoryWriteService.createCategory(requestDto.toDto());
    }
}
