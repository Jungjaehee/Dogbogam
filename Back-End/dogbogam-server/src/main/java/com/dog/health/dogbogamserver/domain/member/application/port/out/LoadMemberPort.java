package com.dog.health.dogbogamserver.domain.member.application.port.out;

import com.dog.health.dogbogamserver.domain.member.domain.Member;

public interface LoadMemberPort {
    Member loadMember(Long memberId);
}