package com.lee.drugescape.drugescape.service;

import com.lee.drugescape.drugescape.dto.SocialAuthResponse;
import com.lee.drugescape.drugescape.dto.SocialUserResponse;
import com.lee.drugescape.drugescape.type.UserType;
import org.springframework.stereotype.Service;

//현재는 구글만 구현 할 것이지만 네이버 카카오 등 추가할 때 인터페이스로 미리만들어두기
@Service
public interface SocialLoginService {
    UserType getServiceName();
    SocialAuthResponse getAccessToken(String authorizationCode);
    SocialUserResponse getUserInfo(String accessToken);

}

