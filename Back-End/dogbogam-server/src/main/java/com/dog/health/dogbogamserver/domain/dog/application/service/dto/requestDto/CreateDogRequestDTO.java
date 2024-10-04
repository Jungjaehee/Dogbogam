package com.dog.health.dogbogamserver.domain.dog.application.service.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateDogRequestDTO {
    @NotBlank(message = "반려견 이름은 필수 입니다.")
    private String name;
    @NotBlank(message = "견종은 필수 입니다.")
    private String breed;
    @NotBlank(message = "성별은 필수 입니다.")
    private String gender;
    private String birthDate;
    private Double weight;
    @NotNull(message = "중성화 여부는 필수 입니다.")
    private Boolean isNeutered;
    private MultipartFile image;
}