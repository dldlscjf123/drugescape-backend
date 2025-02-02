package com.lee.drugescape.drugescape.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoogleLoginResponse {
    private String id;
    private String name;
    private String email;
    private String picture;
}
