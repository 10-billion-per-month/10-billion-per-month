package com.example.dev.controller;

import com.example.dev.dto.request.*;
import com.example.dev.dto.response.MenuResponseDto;
import com.example.dev.dto.response.MenusResponseDto;
import com.example.dev.service.MenuReadService;
import com.example.dev.service.MenuWriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuReadService menuReadService;
    private final MenuWriteService menuWriteService;

    /**
     * 메뉴 등록
     * @param requestDto
     */
    @PostMapping("/v1/menu")
    public void createMenu(CreateMenuRequestDto requestDto) {
        menuWriteService.createMenu(requestDto.toDto());
    }

    /**
     * 가게 메뉴 목록 조회
     * @param requestDto
     * @return
     */
    @GetMapping("/v1/menus")
    public Page<MenusResponseDto> getMenus(@PageableDefault Pageable pageable, MenusRequestDto requestDto) {
        return menuReadService.getMenus(requestDto.toDto(), pageable).map(MenusResponseDto::toResponseDto);
    }

    /**
     * 메뉴 상세 조회
     * @param requestDto
     * @return
     */
    @GetMapping("/v1/menu")
    public MenuResponseDto getMenu(@Valid MenuRequestDto requestDto) {
        return MenuResponseDto.from(menuReadService.getMenu(requestDto.toDto()));
    }

    /**
     * 메뉴 수정
     */
    @PutMapping
    public void modifyMenu(@Valid ModifyMenuRequestDto requestDto) {
        menuWriteService.modifyMenu(requestDto.toDto());
    }

    @DeleteMapping("/v1/menu")
    public void deleteMenu(@Valid DeleteMenuRequestDto requestDto) {
        menuWriteService.deleteMenu(requestDto.toDto());
    }

}
