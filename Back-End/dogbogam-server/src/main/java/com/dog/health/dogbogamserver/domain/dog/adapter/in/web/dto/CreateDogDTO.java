package com.dog.health.dogbogamserver.domain.dog.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CreateDogDTO {
    @NotBlank(message = "멤머는 필수 입니다.")
    private Long memberId;
    @NotBlank(message = "반려견 이름은 필수 입니다.")
    private String name;
    @NotBlank(message = "견종은 필수 입니다.")
    private String breed;
    @NotBlank(message = "성별은 필수 입니다.")
    private String gender;
    private LocalDate birthDate;
    private Double weight;
    private Boolean isNeutered;
    private boolean isDeleted;
    private String imageName;
    private String imageUrl;
}
