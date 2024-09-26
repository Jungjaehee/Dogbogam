package com.dog.health.dogbogamserver.domain.dog.domain;

import com.dog.health.dogbogamserver.domain.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class Dog {
    private Long dogId;
    private Member member;
    private String name;
    private String breed;
    private String gender;
    private LocalDate birthDate;
    private Double weight;
    private Boolean isNeutered;
    private String imageName;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Boolean isDeleted;

    @Builder
    public Dog(Long dogId, Member member, String name, String breed, String gender, LocalDate birthDate, Double weight, Boolean isNeutered, String imageName, String imageUrl, LocalDateTime createdAt, LocalDateTime modifiedAt, Boolean isDeleted){
        this.dogId = dogId;
        this.member = member;
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.birthDate = birthDate;
        this.weight = weight;
        this.isNeutered = isNeutered;
        this.imageName = imageName;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.isDeleted = isDeleted;
    }

}
