package com.dog.health.dogbogamserver.domain.vaccinationRecords.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.vaccinationRecords.application.port.out.*;
import com.dog.health.dogbogamserver.domain.vaccinationRecords.domain.VaccinationRecord;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class VaccinationRecordPersistenceAdapter implements CreateVaccinationRecordPort, UpdateVaccinationRecordPort,
        FindVaccinationRecordPort, DeleteVaccinationRecordPort, FindVaccinationRecordsPort {
    private final VaccinationRecordSpringDataRepository jpaRepository;
    private final VaccinationRecordMapper VaccinationRecordMapper;

    @Override
    public Optional<VaccinationRecord> findVaccinationRecordById(Long reportId) {
        VaccinationRecordEntity vaccinationRecordEntity = jpaRepository.findById(reportId)
                .orElseThrow(()-> new IllegalArgumentException("없는 리포트 입니다."));
        return Optional.ofNullable(VaccinationRecordMapper.toDomain(vaccinationRecordEntity));
    }

    @Override
    public void createVaccinationRecord(VaccinationRecordEntity vaccinationRecordEntity) {
        log.info("Adapter 예방 접종 기록 등록 : {}", vaccinationRecordEntity);
        jpaRepository.save(vaccinationRecordEntity);
    }

    @Override
    public void deleteVaccinationRecord(Long vaccinationRecordId) {
        log.info("Adapter delete : {}", vaccinationRecordId);
        VaccinationRecordEntity vaccinationRecordEntity = jpaRepository.findById(vaccinationRecordId)
                .orElseThrow(()-> new IllegalArgumentException("없는 리포트 입니다."));

        log.info("Adapter delete entity : {}", vaccinationRecordEntity.getVaccinationRecordId());
        jpaRepository.delete(vaccinationRecordEntity);
    }

    @Override
    public List<VaccinationRecord> findVaccinationRecordsByDogId(Long dogId) {
        log.info("Adapter 예방 접종 기록 리스트 : {}", dogId);

        // 레포지토리에서 reportId로 VaccinationRecordEntity 리스트 조회
        List<VaccinationRecordEntity> vaccinationRecordEntities = jpaRepository.findAllByDog_DogId(dogId);

        // VaccinationRecordEntity 리스트를 VaccinationRecord 도메인 리스트로 변환
        List<VaccinationRecord> vaccinationRecords = vaccinationRecordEntities.stream()
                .map(VaccinationRecordMapper::toDomain) // mapper를 사용하여 엔티티를 도메인 객체로 변환
                .collect(Collectors.toList());

        // Optional로 감싸서 반환
        return vaccinationRecords;
    }

    @Override
    public void updateVaccinationRecord(VaccinationRecordEntity vaccinationRecordEntity) {
        log.info("Adapter 예방 접종 기록 수정 : {}", vaccinationRecordEntity);
        jpaRepository.save(vaccinationRecordEntity);
    }
}
