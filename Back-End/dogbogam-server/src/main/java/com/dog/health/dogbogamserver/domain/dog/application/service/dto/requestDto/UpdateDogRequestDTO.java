package com.dog.health.dogbogamserver.domain.dog.application.service.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class UpdateDogRequestDTO {
    @NotNull(message = "반려견은 필수 입니다.")
    private Long dogId;
    private String name;
    private String breed;
    private String birthDate;
    private Double weight;
    @NotNull(message = "중성화 여부는 필수 입니다.")
    private Boolean isNeutered;
    private MultipartFile image;
}
