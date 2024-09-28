package com.dog.health.dogbogamserver.domain.member.application.port.in;

import com.dog.health.dogbogamserver.domain.member.application.service.dto.request.LoginRequest;
import com.dog.health.dogbogamserver.domain.member.application.service.dto.response.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginRequest request);
}
