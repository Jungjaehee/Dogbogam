package com.dog.health.dogbogamserver.domain.dog.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UpdateDogDTO {
    @NotBlank(message = "반려견은 필수 입니다.")
    private Long dogId;
    @NotBlank(message = "멤버는 필수 입니다.")
    private Long memberId;
    @NotBlank(message = "반려견 이름은 필수 입니다.")
    private String name;
    @NotBlank(message = "견종은 필수 입니다.")
    private String breed;
    @NotBlank(message = "성별은 필수 입니다.")
    private LocalDate birthDate;
    private Double weight;
    private Boolean isNeutered;
    private String imageName;
    private String imageUrl;
}
