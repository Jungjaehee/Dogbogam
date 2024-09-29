package com.dog.health.dogbogamserver.domain.member.application.service.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor
public class MemberResponse {
    private Long memberId;
    private String email;
    private String nickname;

    @Builder
    public MemberResponse(Long memberId, String email, String nickname) {
        this.memberId = memberId;
        this.email = email;
        this.nickname = nickname;
    }
}
