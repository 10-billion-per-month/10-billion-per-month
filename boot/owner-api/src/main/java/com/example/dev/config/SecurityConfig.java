package com.example.dev.config;


import com.example.dev.config.filter.JwtTokenFilter;
import com.example.dev.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity(debug = true) // 무슨 필터 탔는지 나옴
@RequiredArgsConstructor
public class SecurityConfig {

    private String key;
    private final OwnerRepository ownerRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                                .requestMatchers("/api/*/users/join", "/api/*/users/login", "/error").permitAll() // 모두 허용
//                        .requestMatchers("/", "/home").hasRole("USER")
                                .anyRequest().authenticated()
                )
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 안쓰겠다.
                .and()
                .csrf(AbstractHttpConfigurer::disable) // csrf 안씀 - 쓸 경우 토큰이 없을 경우 예외 발생
                .formLogin().disable()
//                .cors().configurationSource(corsConfigurationSource())
//                .and()
                .addFilterBefore(new JwtTokenFilter(key, ownerRepository), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(new ExceptionHandlerFilter(), JwtTokenFilter.class)
//                .addFilterAfter (new AuthorityFilter(), JwtTokenFilter.class)
                .exceptionHandling()
//                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
        ;
        return http.build();
    }


    // CORS 허용 적용
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
