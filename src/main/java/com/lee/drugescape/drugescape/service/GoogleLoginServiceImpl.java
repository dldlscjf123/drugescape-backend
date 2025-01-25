package com.lee.drugescape.drugescape.service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lee.drugescape.drugescape.dto.GoogleLoginResponse;
import com.lee.drugescape.drugescape.dto.GoogleRequestAccessTokenDto;
import com.lee.drugescape.drugescape.dto.SocialAuthResponse;
import com.lee.drugescape.drugescape.dto.SocialUserResponse;
import com.lee.drugescape.drugescape.feign.google.GoogleAuthApi;
import com.lee.drugescape.drugescape.feign.google.GoogleUserApi;
import com.lee.drugescape.drugescape.type.UserType;
import com.lee.drugescape.drugescape.util.GsonLocalDateTimeAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
@Qualifier("googleLogin")
public class GoogleLoginServiceImpl implements SocialLoginService {
    private final GoogleAuthApi googleAuthApi;
    private final GoogleUserApi googleUserApi;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleAppKey;
    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleAppSecret;
    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String googleRedirectUri;
    @Value("${spring.security.oauth2.client.registration.google.authorization-grant-type}")
    private String googleGrantType;

    @Override
    public UserType getServiceName() {
        return UserType.GOOGLE;
    }

    @Override
    public SocialAuthResponse getAccessToken(String authorizationCode) {
       String decodedCode = decodeCode(authorizationCode);
        GoogleRequestAccessTokenDto requestAccessTokenDto = GoogleRequestAccessTokenDto.builder()
                .code(decodedCode)
                .client_id(googleAppKey)
                .client_secret(googleAppSecret)
                .redirect_uri(googleRedirectUri)
                .grant_type(googleGrantType)
                .build();
        ResponseEntity<?> response = googleAuthApi.getAccessToken(requestAccessTokenDto);
        log.info("API Response Body: {}", response.getBody());

        return new Gson()
                .fromJson(
                        response.getBody().toString(),
                        SocialAuthResponse.class
                );
    }

    @Override
    public SocialUserResponse getUserInfo(String accessToken) {
        ResponseEntity<?> response = googleUserApi.getUserInfo(accessToken);

        log.info("google user response");
        log.info(response.toString());

        String jsonString = response.getBody().toString();

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter())
                .create();

        GoogleLoginResponse googleLoginResponse = gson.fromJson(jsonString, GoogleLoginResponse.class);

        return SocialUserResponse.builder()
                .id(googleLoginResponse.getId())
                .name(googleLoginResponse.getName())
                .email(googleLoginResponse.getEmail())
                .picture(googleLoginResponse.getPicture())
                .build();
    }
    private String decodeCode(String encodedCode) {
        return URLDecoder.decode(encodedCode, StandardCharsets.UTF_8);
    }
}