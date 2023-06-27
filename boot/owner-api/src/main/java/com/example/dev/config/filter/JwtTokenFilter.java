package com.example.dev.config.filter;

import com.example.dev.exception.CommonException;
import com.example.dev.exception.ErrorCode;
import com.example.dev.repository.OwnerRepository;
import com.example.dev.utils.JwtTokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final String key;
    private final OwnerRepository ownerRepository;

    @Override

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //get header
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) throw new CommonException(ErrorCode.UNAUTHORIZED_TOKEN);
//        if (header == null || !header.startsWith("Bearer ")) {
//            log.error("Authorization Header does not start with Bearer {} \n", request.getRequestURL());
//            throw new CommonException(ErrorCode.UNAUTHORIZED_TOKEN);
////            filterChain.doFilter(request, response);
//        }

        try {
            final String token = header.split(" ")[1].trim();
            if (JwtTokenUtils.isExpired(token, key)) throw new CommonException(ErrorCode.EXPIRED_TOKEN);

//            if (JwtTokenUtils.isExpired(token, key)) {
//                throw new CommonException(ErrorCode.EXPIRED_TOKEN);
////                filterChain.doFilter(request, response);
//            }

            String ownerId = JwtTokenUtils.getOwnerId(token, key);
            ownerRepository.findById(Long.parseLong(ownerId))
                    .orElseThrow(() -> new CommonException(ErrorCode.UNAUTHORIZED_TOKEN));

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    ownerId, null);

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (RuntimeException e) {
            log.error("Error occurs while validating. {}", e.toString());
            filterChain.doFilter(request, response);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
