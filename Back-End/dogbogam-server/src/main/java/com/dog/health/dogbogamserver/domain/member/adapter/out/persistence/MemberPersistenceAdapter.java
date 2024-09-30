package com.dog.health.dogbogamserver.domain.member.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.member.application.port.out.LoadMemberPort;
import com.dog.health.dogbogamserver.domain.member.application.port.out.SaveMemberPort;
import com.dog.health.dogbogamserver.domain.member.domain.Member;
import com.dog.health.dogbogamserver.global.web.exception.CustomException;
import com.dog.health.dogbogamserver.global.web.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements LoadMemberPort, SaveMemberPort {

    private final MemberSpringDataRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public Member loadMember(Long memberId) {
        return memberRepository.findById(memberId)
                .map(memberMapper::toDomain)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public Long saveMember(Member member){
        MemberEntity memberEntity = memberMapper.toEntity(member);
        memberRepository.save(memberEntity);
        return memberEntity.getMemberId();

    }

    @Override
    public Optional<Member> loadMemberByEmail(String email){
        return memberRepository.findByEmail(email)
                .map(memberMapper::toDomain);
    }
}
