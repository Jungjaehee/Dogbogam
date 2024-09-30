package com.dog.health.dogbogamserver.domain.member.application.port.in;

import com.dog.health.dogbogamserver.domain.member.application.service.dto.response.CheckResponse;
import com.dog.health.dogbogamserver.domain.member.application.service.dto.request.CheckRequest;

public interface CheckMemberUseCase {
    CheckResponse checkDuplicateEmail(CheckRequest request);
}
