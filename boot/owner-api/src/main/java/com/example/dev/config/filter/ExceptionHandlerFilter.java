package com.example.dev.config.filter;

import com.example.dev.exception.CommonException;
import com.example.dev.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (CommonException e) {
            log.error("Error occurs while validating. {}", e.toString());
            ObjectMapper objectMapper = new ObjectMapper();
            ApiResponse<Object> apiResponse = ApiResponse.of(e.getErrorCode().getStatus(), e.getErrorCode().getMessage());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
        }
    }
}
