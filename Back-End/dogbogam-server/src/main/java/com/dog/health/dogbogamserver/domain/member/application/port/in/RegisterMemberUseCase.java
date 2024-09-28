package com.dog.health.dogbogamserver.domain.member.application.port.in;

import com.dog.health.dogbogamserver.domain.member.application.service.dto.request.CreateRequest;
import com.dog.health.dogbogamserver.domain.member.application.service.dto.response.LoginResponse;


public interface RegisterMemberUseCase {
    LoginResponse registerMember(CreateRequest request);
}
