package com.dog.health.dogbogamserver.domain.member.adapter.in.web;

import com.dog.health.dogbogamserver.domain.member.application.port.in.CheckMemberUseCase;
import com.dog.health.dogbogamserver.domain.member.application.port.in.LoginUseCase;
import com.dog.health.dogbogamserver.domain.member.application.port.in.RegisterMemberUseCase;
import com.dog.health.dogbogamserver.domain.member.application.service.dto.request.CheckRequest;
import com.dog.health.dogbogamserver.domain.member.application.service.dto.request.CreateRequest;
import com.dog.health.dogbogamserver.domain.member.application.service.dto.request.LoginRequest;
import com.dog.health.dogbogamserver.domain.member.application.service.dto.response.CheckResponse;
import com.dog.health.dogbogamserver.domain.member.application.service.dto.response.LoginResponse;
import com.dog.health.dogbogamserver.global.web.dto.response.SuccessResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final RegisterMemberUseCase registerMemberUseCase;
    private final LoginUseCase loginUseCase;
    private final CheckMemberUseCase checkMemberUseCase;

    // 회원가입
    @PostMapping()
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
    public SuccessResponse<?> checkDuplicateEmail(@RequestBody CheckRequest checkRequest) throws JsonProcessingException {
        CheckResponse checkResponse = checkMemberUseCase.checkDuplicateEmail(checkRequest);
        return SuccessResponse.ok(checkResponse);
    }
}
