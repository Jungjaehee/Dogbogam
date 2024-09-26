package com.dog.health.dogbogamserver.domain.member.application.service;

import com.dog.health.dogbogamserver.domain.member.application.port.out.LoadMemberPort;
import com.dog.health.dogbogamserver.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final LoadMemberPort loadMemberPort;

    // memberId로 DB에서 정보 가져오기
    public Member findByMemberId(Long memberId){
        return loadMemberPort.loadMember(memberId);
    }
}