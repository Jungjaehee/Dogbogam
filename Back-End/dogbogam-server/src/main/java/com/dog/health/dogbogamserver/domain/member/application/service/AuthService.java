package com.dog.health.dogbogamserver.domain.member.application.service;

import com.dog.health.dogbogamserver.domain.member.application.port.in.LoginUseCase;
import com.dog.health.dogbogamserver.domain.member.application.port.out.LoadMemberPort;
import com.dog.health.dogbogamserver.domain.member.application.service.dto.request.LoginRequest;
import com.dog.health.dogbogamserver.domain.member.application.service.dto.response.LoginResponse;
import com.dog.health.dogbogamserver.domain.member.domain.Member;
import com.dog.health.dogbogamserver.global.auth.utils.JWTProvider;
import com.dog.health.dogbogamserver.global.web.exception.CustomException;
import com.dog.health.dogbogamserver.global.web.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements LoginUseCase {

    private final LoadMemberPort loadMemberPort;
    private final JWTProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    // 로그인
    @Override
    public LoginResponse login(LoginRequest request){
        Member member = loadMemberPort.loadMemberByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())){
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        String accessToken = jwtProvider.buildAccessToken(member.getMemberId());
        return LoginResponse.createLoginResponse(accessToken);
    }
}
