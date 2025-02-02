package com.lee.drugescape.drugescape.service;

import com.lee.drugescape.drugescape.dto.*;
import com.lee.drugescape.drugescape.exception.NotFoundException;
import com.lee.drugescape.drugescape.repository.UserRepository;
import com.lee.drugescape.drugescape.type.UserType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.lee.drugescape.drugescape.entity.User;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final List<SocialLoginService> loginServices;
    private final UserRepository userRepository;

    public LoginResponse doSocialLogin(SocialLoginRequest request) {
        SocialLoginService loginService = this.getLoginService(request.getUserType());

        SocialAuthResponse socialAuthResponse = loginService.getAccessToken(request.getCode());
        SocialUserResponse socialUserResponse = loginService.getUserInfo(socialAuthResponse.getAccess_token());
        log.info("Refresh Token: {}", socialAuthResponse.getRefresh_token());
        log.info("User: {}", socialUserResponse.getName());
        if (userRepository.findByUserId(socialUserResponse.getId()).isEmpty()) {
            this.joinUser(
                    UserJoinRequest.builder()
                            .userId(socialUserResponse.getId())
                            .userEmail(socialUserResponse.getEmail())
                            .userName(socialUserResponse.getName())
                            .userType(request.getUserType())
                            .build()
            );
        }
        User user = userRepository.findByUserId(socialUserResponse.getId())
                .orElseThrow(() -> new NotFoundException("ERROR_001", "유저 정보를 찾을 수 없습니다."));
        log.info("User: {}", user);
        return LoginResponse.builder()
                .id(user.getId())
                .build();
    }
    private UserJoinResponse joinUser(UserJoinRequest userJoinRequest) {
        User user = userRepository.save(
                User.builder()
                        .userId(userJoinRequest.getUserId())
                        .userType(userJoinRequest.getUserType())
                        .userEmail(userJoinRequest.getUserEmail())
                        .userName(userJoinRequest.getUserName())
                        .build()
        );

        return UserJoinResponse.builder()
                .id(user.getId())
                .build();
    }

    private SocialLoginService getLoginService(UserType userType){
        for (SocialLoginService loginService: loginServices) {
            if (userType.equals(loginService.getServiceName())) {
                log.info("login service name: {}", loginService.getServiceName());
                return loginService;
            }
        }
        return new LoginServiceImpl();
    }
    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id)
               .orElseThrow(() -> new NotFoundException("ERROR_001", "유저 정보를 찾을 수 없습니다."));

        return UserResponse.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .userEmail(user.getUserEmail())
                .userName(user.getUserName())
                .build();
    }

}
