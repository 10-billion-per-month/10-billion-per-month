package com.example.dev.controller;

import com.example.dev.dto.request.MenusRequestDto;
import com.example.dev.service.MenuReadService;
import com.example.dev.service.MenuWriteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(controllers = MenuController.class)
class MenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MenuReadService menuReadService;
    @MockBean
    private MenuWriteService menuWriteService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getMenus() throws Exception {
        // given : 무엇을 할것인가? 데이터 세팅
        String param = "정의장";
//        BDDMockito.given(menuReadService.test(param)).willReturn(param);

        // when : 실제 수행 then : 수행 결과 확인
        mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/menus")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("size", "3")
                        .param("storeId", "1L")
                )
                .andReturn().getResponse().getContentAsByteArray()
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect()
        ;

    }

}