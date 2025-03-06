package com.moduda.api.moduleapi.user.controller;

import com.moduda.api.moduleapi.auth.CustomUserDetails;
import com.moduda.api.moduleapi.user.dto.request.LoginRequest;
import com.moduda.api.moduleapi.user.dto.request.RefreshTokenRequest;
import com.moduda.api.moduleapi.user.dto.response.TokenResponse;
import com.moduda.api.moduleapi.user.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService AuthService;

    public AuthController(AuthService AuthService) {
        this.AuthService = AuthService;
    }

    @PostMapping("/login")
    public TokenResponse login(@Valid @RequestBody LoginRequest loginRequest){
        return AuthService.login(loginRequest);
    }

    @PostMapping("/logout")
    public void logout(@AuthenticationPrincipal CustomUserDetails userDetails){
        AuthService.logout(userDetails);
    }

    @PostMapping("/token/reissue")
    public TokenResponse tokenReissue(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return AuthService.tokenReissue(refreshTokenRequest);
    }
}
