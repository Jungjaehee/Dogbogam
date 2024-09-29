package com.dog.health.dogbogamserver.domain.dog.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CreateDogDTO {
    @NotNull(message = "멤버는 필수 입니다.")
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
    @NotNull(message = "중성화 여부는 필수 입니다.")
    private Boolean isDeleted;
    private String imageName;
    private String imageUrl;
}
