package com.dog.health.dogbogamserver.domain.medicalRecords.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogMapper;
import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogPersistenceAdapter;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.CreateReportRequestDto;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.UpdateReportRequestDto;
import com.dog.health.dogbogamserver.domain.medicalRecords.domain.MedicalRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MedicalRecordMapper {

    private final DogMapper dogMapper;
    private final DogPersistenceAdapter dogPersistenceAdapter;

    public MedicalRecord toDomain(MedicalRecordEntity entity) {
        if (entity == null) {
            return null;
        }

        return MedicalRecord.builder()
                .medicalRecordId(entity.getMedicalRecordId())
                .dog(dogMapper.toDomain(entity.getDog()))
                .recordDate(entity.getRecordDate())
                .content(entity.getContent())
                .hospital(entity.getHospital())
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
                .recordDate(domain.getRecordDate())
                .content(domain.getContent())
                .hospital(domain.getHospital())
                .imageName(domain.getImageName())
                .imageUrl(domain.getImageUrl())
                .build();
    }

    public MedicalRecordEntity toEntity(CreateReportRequestDto createReportRequestDto) {
        if (createReportRequestDto == null) {
            return null;
        }
        return MedicalRecordEntity.builder()
                .dog(dogMapper.toEntity(dogPersistenceAdapter.findByDogId(createReportRequestDto.getDogId()).get()))
                .recordDate(createReportRequestDto.getRecordDate())
                .content(createReportRequestDto.getContent())
                .hospital(createReportRequestDto.getHospital())
                // 추후 s3 추가되면 수정
//                .imageName(createReportDto.getImageName())
//                .imageUrl(createReportDto.getImageUrl())
                .build();
    }

    public MedicalRecordEntity toEntity(UpdateReportRequestDto updateReportRequestDto) {
        if (updateReportRequestDto == null) {
            return null;
        }
        return MedicalRecordEntity.builder()
                .medicalRecordId(updateReportRequestDto.getReportId())
//                .dog()
                .content(updateReportRequestDto.getContent())
                .recordDate(updateReportRequestDto.getRecordDate())
                .hospital(updateReportRequestDto.getHospital())
                // 이미지 파일 업로드
                .build();
    }
}
