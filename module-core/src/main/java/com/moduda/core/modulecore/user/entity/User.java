package com.moduda.core.modulecore.user.entity;

import com.moduda.core.modulecore.BaseEntity;
import com.moduda.core.modulecore.user.enums.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "user_id",nullable = false, updatable = false )
    private String userId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "encoded_password", nullable = false)
    private String encodedPassword;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "grant_type")
    private Role role = Role.ROLE_USER;

    @Builder
    public User(String userId, String email, String nickname, LocalDate birth) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.birth = birth;
    }

    public void updateProfile(String nickname, String email, LocalDate birth) {
        this.nickname = nickname;
        this.email = email;
        this.birth = birth;
    }

    public void updatePassword(String newEncodedPassword){
        this.encodedPassword = newEncodedPassword;
    }
}
