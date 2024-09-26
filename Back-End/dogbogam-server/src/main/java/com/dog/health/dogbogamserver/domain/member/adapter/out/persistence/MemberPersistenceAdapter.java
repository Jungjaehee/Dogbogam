package com.dog.health.dogbogamserver.domain.member.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.member.application.port.out.LoadMemberPort;
import com.dog.health.dogbogamserver.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements LoadMemberPort {

    private final MemberSpringDataRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public Member loadMember(Long memberId) {
        return memberRepository.findById(memberId)
                .map(memberMapper::toDomain)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다."));
    }
}