package com.example.dev.controller;

import com.example.dev.service.OrderWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderWriteService orderWriteService;
}
