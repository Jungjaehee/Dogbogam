package com.dog.health.dogbogamserver.domain.member.application.port.out;

import com.dog.health.dogbogamserver.domain.member.domain.Member;

import java.util.Optional;

public interface LoadMemberPort {
    Member loadMember(Long memberId);

    Optional<Member> loadMemberByEmail(String email);
}
