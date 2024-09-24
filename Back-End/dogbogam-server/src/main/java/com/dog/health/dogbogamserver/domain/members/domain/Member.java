package com.dog.health.dogbogamserver.domain.members.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Member {

    private Long memberId;
    private String email;
    private String password;
    private String nickname;
    private Boolean isDeleted;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    @Builder
    public Member(Long memberId, String email, String password, String nickname, Boolean isDeleted, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.memberId = memberId;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.isDeleted = isDeleted;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}
