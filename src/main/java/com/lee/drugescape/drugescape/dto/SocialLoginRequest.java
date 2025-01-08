package com.lee.drugescape.drugescape.dto;

import com.lee.drugescape.drugescape.type.UserType;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

// 인증코드와 어떤경로로 로그인했는지 알려주는 userType이 로그인 요청 필수
@Getter
public class SocialLoginRequest {
    @NotNull
    private UserType userType;
    @NotNull
    private String code;

}
