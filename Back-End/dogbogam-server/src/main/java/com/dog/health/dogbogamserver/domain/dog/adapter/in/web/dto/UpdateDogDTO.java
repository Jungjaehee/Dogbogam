package com.dog.health.dogbogamserver.domain.dog.adapter.in.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateDogDTO {
    private Long dogId;
    private String name;
    private String breed;
    private String gender;
    private LocalDate birthDate;
    private Double weight;
    private Boolean isNeutered;
    private String imageName;
    private String imageUrl;
}
