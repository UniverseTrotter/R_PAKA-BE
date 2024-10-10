package com.ohgiraffers.r_pakabe.domains.user.command.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
@Entity
@Table(name = "TBL_USER")
public class User {

    @Id
    @Column(name="USER_CODE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userCode;

    @Column(unique = true, nullable = false)
    private String userId;

    @Column(unique = true, nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Builder
    public User(String userId, String password, String nickname) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                "userCode=" + userCode +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
