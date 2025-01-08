package com.lee.drugescape.drugescape.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.web.SecurityFilterChain;



@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        HttpSecurity httpSecurity = http
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/oauth2/**", "/login/**", "http://localhost:8080", "/login2/**").permitAll() // OAuth 관련 요청 허용
                        .anyRequest().authenticated() // 나머지는 인증 필요
                )
                .oauth2Login(Customizer.withDefaults());// OAuth2 설정 추가
        return http.build();
    }
}





