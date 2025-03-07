package com.moduda.api.moduleapi.user.dto.response;

import com.moduda.core.modulecore.user.entity.User;

import java.time.LocalDate;

public class UserResponse {
    public record Detail(
            Long id,
            String nickname,
            String userId,
            String email,
            LocalDate birth
    ) {
        public static Detail from(User user) {
            return new Detail(user.getId(),
                              user.getNickname(),
                              user.getUserId(),
                              user.getEmail(),
                              user.getBirth());
        }
    }

    public record Summary(
            Long id,
            String userId,
            String nickname
    ){
        public static Summary from(User user) {
            return new Summary(user.getId(),
                               user.getUserId(),
                               user.getNickname());
        }
    }
}
