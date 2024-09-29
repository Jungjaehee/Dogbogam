package com.dog.health.dogbogamserver.domain.member.application.port.in;

import com.dog.health.dogbogamserver.domain.member.application.service.dto.response.MemberResponse;

public interface FindMemberUseCase {
    MemberResponse findMember(Long memberId);
}
