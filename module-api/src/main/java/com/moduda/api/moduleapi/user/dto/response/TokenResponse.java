package com.moduda.api.moduleapi.user.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

public record TokenResponse(
        UserResponse.Summary userResponse,
        String accessToken,
        String refreshToken
) {

    public static TokenResponse from(UserResponse.Summary userResponse, String accessToken, String refreshToken){
        return new TokenResponse(userResponse, accessToken, refreshToken);
    }
}
