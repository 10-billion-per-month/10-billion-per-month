package com.example.dev.controller;

import com.example.dev.dto.request.CreateMenuRequestDto;
import com.example.dev.dto.request.MenusRequestDto;
import com.example.dev.dto.response.MenusResponseDto;
import com.example.dev.service.MenuReadService;
import com.example.dev.service.MenuWriteService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
    public Page<MenusResponseDto> getMenus(MenusRequestDto requestDto) {
        return menuReadService.getMenus(requestDto.toDto()).map(MenusResponseDto::toResponseDto);
    }
}
