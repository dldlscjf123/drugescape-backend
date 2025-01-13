package com.lee.drugescape.drugescape.dto;


import com.lee.drugescape.drugescape.type.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserJoinRequest {
    private String userId;
    private String userName;
    private UserType userType;
    private String userEmail;
}