package com.lee.drugescape.drugescape.service;

import com.lee.drugescape.drugescape.dto.SocialAuthResponse;
import com.lee.drugescape.drugescape.dto.SocialUserResponse;
import com.lee.drugescape.drugescape.type.UserType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Component
@Qualifier("defaultLoginService")
public class LoginServiceImpl implements SocialLoginService {
    @Override
    public UserType getServiceName() {
        return UserType.NORMAL;
    }

    @Override
    public SocialAuthResponse getAccessToken(String authorizationCode) {
        return null;
    }

    @Override
    public SocialUserResponse getUserInfo(String accessToken) {
        return null;
    }
}
