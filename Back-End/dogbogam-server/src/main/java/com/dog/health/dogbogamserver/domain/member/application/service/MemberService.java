package com.dog.health.dogbogamserver.domain.member.application.service;

import com.dog.health.dogbogamserver.domain.member.application.port.in.FindMemberUseCase;
import com.dog.health.dogbogamserver.domain.member.application.port.in.RegisterMemberUseCase;
import com.dog.health.dogbogamserver.domain.member.application.port.out.LoadMemberPort;
import com.dog.health.dogbogamserver.domain.member.application.port.out.SaveMemberPort;
import com.dog.health.dogbogamserver.domain.member.application.service.dto.request.CreateRequest;
import com.dog.health.dogbogamserver.domain.member.application.service.dto.response.LoginResponse;
import com.dog.health.dogbogamserver.domain.member.application.service.dto.response.MemberResponse;
import com.dog.health.dogbogamserver.domain.member.domain.Member;
import com.dog.health.dogbogamserver.global.auth.utils.JWTProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService implements RegisterMemberUseCase, FindMemberUseCase {

    private final LoadMemberPort loadMemberPort;
    private final SaveMemberPort saveMemberPort;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTProvider jwtProvider;

    // memberId로 DB에서 정보 가져오기
    public Member findByMemberId(Long memberId){
        return loadMemberPort.loadMember(memberId);
    }

    // 회원가입
    @Override
    public LoginResponse registerMember(CreateRequest request){
        Member member = Member.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .createAt(LocalDateTime.now())
                .isDeleted(false)
                .build();
        saveMemberPort.saveMember(member);
        String accessToken = jwtProvider.buildAccessToken(member.getMemberId());
        return LoginResponse.createLoginResponse(accessToken);
    }

    @Override
    public MemberResponse findMember(Long memberId) {
        Member member = loadMemberPort.loadMember(memberId);
        MemberResponse memberResponse = MemberResponse.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
        return memberResponse;
    }

}
