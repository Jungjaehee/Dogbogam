package com.dog.health.dogbogamserver.domain.medicalRecords.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogMapper;
import com.dog.health.dogbogamserver.domain.medicalRecords.domain.MedicalRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MedicalRecordMapper {

    private final DogMapper dogMapper;

    public MedicalRecord toDomain(MedicalRecordEntity entity) {
        if (entity == null) {
            return null;
        }

        return MedicalRecord.builder()
                .medicalRecordId(entity.getMedicalRecordId())
                .dog(dogMapper.toDomain(entity.getDog()))
                .recordTime(entity.getRecordTime())
                .content(entity.getContent())
                .hospital(entity.getHospital())
                .cost(entity.getCost())
                .imageName(entity.getImageName())
                .imageUrl(entity.getImageUrl())
                .build();
    }

    public MedicalRecordEntity toEntity(MedicalRecord domain) {
        if (domain == null) {
            return null;
        }

        return MedicalRecordEntity.builder()
                .medicalRecordId(domain.getMedicalRecordId())
                .dog(dogMapper.toEntity(domain.getDog()))
                .recordTime(domain.getRecordTime())
                .content(domain.getContent())
                .hospital(domain.getHospital())
                .cost(domain.getCost())
                .imageName(domain.getImageName())
                .imageUrl(domain.getImageUrl())
                .build();
    }


}
