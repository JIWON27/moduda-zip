package com.moduda.api.moduleapi.user.dto.request;

import com.moduda.core.modulecore.user.entity.User;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UserRequest {
    public record Join(
            @NotBlank(message = ValidationConst.USERID_VALUE)
            @Size(min = ValidationConst.MIN_USERID_LENGTH, max = ValidationConst.MAX_USERID_LENGTH, message = ValidationConst.USERID_LENGTH)
            String userId,

            @NotBlank(message = ValidationConst.EMAIL_VALUE)
            @Email(message = ValidationConst.EMAIL_FORMAT)
            String email,

            @NotBlank(message = ValidationConst.NICKNAME_VALUE)
            @Pattern(regexp = ValidationConst.NICKNAME_REGEXP, message = ValidationConst.NICKNAME_FORMAT)
            @Size(min = ValidationConst.MIN_NICKNAME_LENGTH, max = ValidationConst.MAX_NICKNAME_LENGTH, message = ValidationConst.NICKNAME_LENGTH)
            String nickname,

            @NotBlank(message = ValidationConst.PASSWORD_VALUE)
            @Pattern(regexp = ValidationConst.PASSWORD_REGEXP, message = ValidationConst.PASSWORD_FORMAT)
            String password,

            @NotNull(message = ValidationConst.BIRTH_VALUE)
            LocalDate birth
    ){
        public User toEntity(){
            return User.builder()
                       .userId(userId())
                       .nickname(nickname())
                       .email(email())
                       .birth(birth())
                       .build();
        }
    }
    public record Update(
            @NotBlank(message = ValidationConst.NICKNAME_VALUE)
            @Pattern(regexp = ValidationConst.NICKNAME_REGEXP, message = ValidationConst.NICKNAME_FORMAT)
            @Size(min = ValidationConst.MIN_NICKNAME_LENGTH, max = ValidationConst.MAX_NICKNAME_LENGTH, message = ValidationConst.NICKNAME_LENGTH)
            String nickname,

            @Email(message = ValidationConst.EMAIL_FORMAT)
            String email,

            @NotNull(message = ValidationConst.BIRTH_VALUE)
            LocalDate birth
    ){}

    public record UpdatePassword(
            @NotBlank(message = ValidationConst.PASSWORD_VALUE)
            String oldPassword,

            @NotBlank(message = ValidationConst.PASSWORD_VALUE)
            @Pattern(regexp=ValidationConst.PASSWORD_REGEXP, message = ValidationConst.PASSWORD_FORMAT)
            String newPassword,

            @NotBlank(message = ValidationConst.PASSWORD_VALUE)
            String confirmPassword
    ){}
}
