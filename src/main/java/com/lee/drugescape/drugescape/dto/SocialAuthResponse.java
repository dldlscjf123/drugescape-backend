package com.lee.drugescape.drugescape.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
// 액세스 토큰 얻으면 여기 저장
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SocialAuthResponse {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private String expires_in;
    private String scope;
    private String refresh_token_expires_in;
}
