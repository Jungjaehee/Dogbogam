package com.dog.health.dogbogamserver.domain.member.application.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CheckRequest {
    @NotBlank(message = "중복 체크할 이메일이 입력되지 않았습니다.")
    String email;
}
