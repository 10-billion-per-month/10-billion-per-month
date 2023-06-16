package com.example.dev.controller;

import com.example.dev.dto.request.CreateMenuRequestDto;
import com.example.dev.dto.request.MenusRequestDto;
import com.example.dev.dto.response.MenusResponseDto;
import com.example.dev.service.MenuReadService;
import com.example.dev.service.MenuWriteService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/v1/menus")
    public List<MenusResponseDto> getMenus(MenusRequestDto requestDto) {
        return menuReadService.getMenus(requestDto.toDto()).stream().map(MenusResponseDto::toResponseDto).collect(Collectors.toList());
    }
}
