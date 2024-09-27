package com.dog.health.dogbogamserver.domain.member.adapter.in.web;

import com.dog.health.dogbogamserver.domain.member.application.port.in.RegisterMemberUseCase;
import com.dog.health.dogbogamserver.domain.member.application.service.MemberService;
import com.dog.health.dogbogamserver.domain.member.application.service.dto.request.CreateRequest;
import com.dog.health.dogbogamserver.domain.member.application.service.dto.response.LoginResponse;
import com.dog.health.dogbogamserver.global.web.dto.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final RegisterMemberUseCase registerMemberUseCase;

    // 회원가입
    @PostMapping
    public SuccessResponse<LoginResponse> createMember(@RequestBody CreateRequest createRequest){
        LoginResponse loginResponse = registerMemberUseCase.registerMember(createRequest);
        return SuccessResponse.created(loginResponse);
    }
}
