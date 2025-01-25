package com.lee.drugescape.drugescape.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 클라이언트가 login2로 post요청시 리턴 값
// 이메일, 이름 , 사용자 사진이 있어야함
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginResponse {
    private String name;
    private String email;
    private String picture;
    private String jwtToken;
}
