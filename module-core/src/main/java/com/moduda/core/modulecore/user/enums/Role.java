package com.moduda.core.modulecore.user.enums;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_USER("회원"),
    ROLE_SELLER("판매자"),
    ROLE_ADMIN("관리자");
    public final String description;

    Role(String description) {
        this.description = description;
    }
}
