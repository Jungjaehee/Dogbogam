package com.dog.health.dogbogamserver.domain.member.application.service.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckResponse {
    private Boolean isDuplicate;
    private String responseMessage;

    @Builder
    private CheckResponse(Boolean isDuplicate, String responseMessage) {
        this.isDuplicate = isDuplicate;
        this.responseMessage = responseMessage;
    }

    public static CheckResponse createCheckResponse(Boolean isDuplicate, String responseMessage) {
        return CheckResponse.builder()
                .isDuplicate(isDuplicate)
                .responseMessage(responseMessage)
                .build();
    }
}
