package com.example.dev.utils;

import com.example.dev.dto.OwnerDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 토큰에 유저네임과 키와 유효시간을 넣어준다.
 */
public class JwtTokenUtils {

    private final String securityKey = "hello world"; // TODO 민감정보는 따로 분리하는 것이 좋다
    private final Long expiredTime = 1000 * 60L * 60L * 3L; // 유효시간 3시간

    public String generateJwtToken(OwnerDto owner) {
        Claims claims = Jwts.claims();
        claims.put("ownerId", owner.getOwnerId());

        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256"); // 해시 256 사용하여 암호화
        header.put("regDate", System.currentTimeMillis());

        Date now = new Date();
        return Jwts.builder()
                .setHeader(header)
                .setSubject(owner.getOwnerId().toString())
                .setClaims(claims)
                .setExpiration(new Date(now.getTime() + expiredTime))
                .signWith(SignatureAlgorithm.HS256, securityKey)
                .compact();
    }

    public static String getOwnerId(String token, String key) {
        return extractClaims(token, key).get("ownerId", String.class);
    }

    public static boolean isExpired(String token, String key) {
        Date expiredDate = extractClaims(token, key).getExpiration();
        return expiredDate.before(new Date());
    }

    private static Claims extractClaims(String token, String key) {
        return Jwts.parserBuilder().setSigningKey(getKey(key))
                .build().parseClaimsJws(token).getBody();
    }


    private static Key getKey(String key) {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}