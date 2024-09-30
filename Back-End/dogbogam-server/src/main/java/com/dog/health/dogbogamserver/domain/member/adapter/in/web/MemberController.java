package com.dog.health.dogbogamserver.domain.member.adapter.in.web;

import com.dog.health.dogbogamserver.domain.member.application.port.in.CheckMemberUseCase;
import com.dog.health.dogbogamserver.domain.member.application.port.in.LoginUseCase;
import com.dog.health.dogbogamserver.domain.member.application.port.in.RegisterMemberUseCase;
import com.dog.health.dogbogamserver.domain.member.application.service.dto.request.CheckRequest;
import com.dog.health.dogbogamserver.domain.member.application.port.in.FindMemberUseCase;
import com.dog.health.dogbogamserver.domain.member.application.service.dto.request.CreateRequest;
import com.dog.health.dogbogamserver.domain.member.application.service.dto.request.LoginRequest;
import com.dog.health.dogbogamserver.domain.member.application.service.dto.response.CheckResponse;
import com.dog.health.dogbogamserver.domain.member.application.service.dto.response.LoginResponse;
import com.dog.health.dogbogamserver.domain.member.application.service.dto.response.MemberResponse;
import com.dog.health.dogbogamserver.global.auth.dto.MemberPrincipal;
import com.dog.health.dogbogamserver.global.web.dto.response.SuccessResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final RegisterMemberUseCase registerMemberUseCase;
    private final FindMemberUseCase findMemberUseCase;
    private final LoginUseCase loginUseCase;
    private final CheckMemberUseCase checkMemberUseCase;

    // 회원가입
    @PostMapping
    public SuccessResponse<LoginResponse> createMember(@RequestBody CreateRequest createRequest){
        LoginResponse loginResponse = registerMemberUseCase.registerMember(createRequest);
        return SuccessResponse.created(loginResponse);
    }

    // 로그인
    @PostMapping("/login")
    public SuccessResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = loginUseCase.login(loginRequest);
        return SuccessResponse.ok(loginResponse);
    }


    // 이메일 중복 체크
    @PostMapping("/check")
    public SuccessResponse<?> checkDuplicateEmail(@Valid @RequestBody CheckRequest checkRequest) throws JsonProcessingException {
        CheckResponse checkResponse = checkMemberUseCase.checkDuplicateEmail(checkRequest);
        return SuccessResponse.ok(checkResponse);
    }

    // 유저 정보 가져오기
    @GetMapping("/info")
    public SuccessResponse<MemberResponse> findMember(@AuthenticationPrincipal MemberPrincipal memberPrincipal){
        MemberResponse memberResponse = findMemberUseCase.findMember(memberPrincipal.getMemberId());
        return SuccessResponse.ok(memberResponse);

    }
}
