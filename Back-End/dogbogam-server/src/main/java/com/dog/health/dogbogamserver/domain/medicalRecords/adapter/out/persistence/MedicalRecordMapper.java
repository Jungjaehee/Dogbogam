package com.dog.health.dogbogamserver.domain.medicalRecords.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.medicalRecords.domain.MedicalRecord;

public class MedicalRecordMapper {
    public static MedicalRecord toDomain(MedicalRecordEntity entity) {
        return new MedicalRecord(
                entity.getId(),
                entity.getDog(),
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
        entity.setDog(domain.getDog());
        entity.setDate(domain.getDate());
        entity.setContent(domain.getContent());
        entity.setHospital(domain.getHospital());
        entity.setImageName(domain.getImageName());
        entity.setImageUrl(domain.getImageUrl());
        return entity;
    }
}
