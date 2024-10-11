package com.dog.health.dogbogamserver.domain.vaccinationRecords.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogMapper;
import com.dog.health.dogbogamserver.domain.medicalRecords.adapter.out.persistence.MedicalRecordEntity;
import com.dog.health.dogbogamserver.domain.medicalRecords.domain.MedicalRecord;
import com.dog.health.dogbogamserver.domain.vaccinationRecords.domain.VaccinationRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VaccinationRecordMapper {

    private final DogMapper dogMapper;

    public VaccinationRecord toDomain(VaccinationRecordEntity entity) {
        if (entity == null) {
            return null;
        }

        return VaccinationRecord.builder()
                .vaccinationRecordId(entity.getVaccinationRecordId())
                .dog(dogMapper.toDomain(entity.getDog()))
                .recordTime(entity.getRecordTime())
                .content(entity.getContent())
                .hospital(entity.getHospital())
                .cost(entity.getCost())
                .vaccinationRound(entity.getVaccinationRound())
                .imageName(entity.getImageName())
                .imageUrl(entity.getImageUrl())
                .build();
    }

    public VaccinationRecordEntity toEntity(VaccinationRecord domain) {
        if (domain == null) {
            return null;
        }

        return VaccinationRecordEntity.builder()
                .vaccinationRecordId(domain.getVaccinationRecordId())
                .dog(dogMapper.toEntity(domain.getDog()))
                .recordTime(domain.getRecordTime())
                .content(domain.getContent())
                .hospital(domain.getHospital())
                .cost(domain.getCost())
                .vaccinationRound(domain.getVaccinationRound())
                .imageName(domain.getImageName())
                .imageUrl(domain.getImageUrl())
                .build();
    }
}
