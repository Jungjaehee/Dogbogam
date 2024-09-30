package com.dog.health.dogbogamserver.domain.member.application.port.out;

import com.dog.health.dogbogamserver.domain.member.domain.Member;

public interface SaveMemberPort {
    Long saveMember(Member member);
}
