package com.moduda.core.modulecore.refreshToken.repository;

import com.moduda.core.modulecore.refreshToken.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    void deleteByUserId(String userId);
    Optional<RefreshToken> findByUserId(String userId);
}
