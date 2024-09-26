package com.dog.health.dogbogamserver.domain.medicalRecords.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in.MedicalRecordService;
import com.dog.health.dogbogamserver.domain.medicalRecords.domain.MedicalRecord;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MedicalRecordMapper {
    private final MedicalRecordService medicalRecordService;


    public static MedicalRecord toDomain(MedicalRecordEntity entity) {
        return new MedicalRecord(
                entity.getId(),
                entity.getDog().getDogId(),
                entity.getDate(),
                entity.getContent(),
                entity.getHospital(),
                entity.getImageName(),
                entity.getImageUrl(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }

    public static MedicalRecordEntity toEntity(MedicalRecord domain) {
        MedicalRecordEntity entity = new MedicalRecordEntity();
//        entity.setDog(domain.getDogId());  // 추후 dog엔터티 생성 후 사용
        entity.setDate(domain.getDate());
        entity.setContent(domain.getContent());
        entity.setHospital(domain.getHospital());
        entity.setImageName(domain.getImageName());
        entity.setImageUrl(domain.getImageUrl());
        return entity;
    }
}
