package com.example.dev.controller;

import com.example.dev.dto.request.CategorysRequestDto;
import com.example.dev.dto.request.CreateCategoryRequestDto;
import com.example.dev.dto.request.ModifyCategoryRequestDto;
import com.example.dev.dto.request.StoresRequestDto;
import com.example.dev.dto.response.CategorysResponseDto;
import com.example.dev.dto.response.StoresResponseDto;
import com.example.dev.service.CategoryReadService;
import com.example.dev.service.CategoryWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryWriteService categoryWriteService;
    private final CategoryReadService categoryReadService;

    /**
     * 카테고리 등록
     * @param requestDto
     */
    @PostMapping("/v1/category")
    public void createCategory(@RequestBody CreateCategoryRequestDto requestDto) {
        categoryWriteService.createCategory(requestDto.toDto());
    }

    /**
     * 카테고리 목록 조회
     * @param requestDto
     * @return
     */
    @GetMapping("/v1/categorys")
    public Page<CategorysResponseDto> getStores(@PageableDefault(size = 10, sort = "categoryId", direction = Sort.Direction.ASC) CategorysRequestDto requestDto) {
        return categoryReadService.getCategorys(requestDto.toDto()).map(CategorysResponseDto::toResponseDto);
    }

    /**
     * 카테고리 수정
     * @param requestDto
     */
    @PutMapping("/v1/category")
    public void modifyCategory(@RequestBody ModifyCategoryRequestDto requestDto) {
        categoryWriteService.modifyCategory(requestDto.toDto());
    }
}
