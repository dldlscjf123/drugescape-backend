package com.lee.drugescape.drugescape.controller;

import com.lee.drugescape.drugescape.dto.LoginResponse;
import com.lee.drugescape.drugescape.dto.SocialLoginRequest;
import com.lee.drugescape.drugescape.dto.UserResponse;
import com.lee.drugescape.drugescape.service.UserService;
import com.lee.drugescape.drugescape.type.UserType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login2")
    public ResponseEntity<LoginResponse> doSocialLogin(@RequestBody @Valid SocialLoginRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("Validation errors: {}", bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body(null);  // 400 Bad Request
        }log.info("doSocialLogin method called with request: {}", request);
        return ResponseEntity.created(URI.create("/login2"))
                .body(userService.doSocialLogin(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                userService.getUser(id)
        );
    }
}
