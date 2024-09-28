package com.dog.health.dogbogamserver.domain.insuranceRecord.application.service;

import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.in.dto.RegistRequestDto;
import com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.in.RegistInsuranceRecordUseCase;
import com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.out.SaveInsuranceRecordPort;
import com.dog.health.dogbogamserver.domain.insuranceRecord.domain.InsuranceRecord;
import com.dog.health.dogbogamserver.domain.insurance.domain.Insurance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InsuranceRecordService implements RegistInsuranceRecordUseCase {

    private final SaveInsuranceRecordPort saveInsuranceRecordPort;

    @Override
    public void registInsuranceRecord(RegistRequestDto registRequestDto){
        // 1. 이미 해당 펫이 해당 보험을 등록했었는지 검사
        Optional<InsuranceRecord> existInsuranceRecord = saveInsuranceRecordPort
                .checkExistingInsuranceRecord(registRequestDto.getDogId(), registRequestDto.getInsuranceId());

        if(existInsuranceRecord.isPresent()){
            throw new IllegalArgumentException("이미 등록된 보험입니다.");
        }

        // 2. 해당 펫 보험이 존재하는지 검사
        Insurance insurance = saveInsuranceRecordPort.checkExistingInsurance(registRequestDto.getInsuranceId())
                .orElseThrow(() -> new IllegalArgumentException("해당 펫보험이 존재하지 않습니다."));
        
        // 3. 해당 펫이 존재하는지 검사
        Dog dog = saveInsuranceRecordPort.checkExistingDog(registRequestDto.getDogId());

        // 4. 해당 정보 저장
        InsuranceRecord insuranceRecord = InsuranceRecord.builder()
                .insurance(insurance)
                .dog(dog)
                .registDate(registRequestDto.getRegistDate())
                .expirationDate(registRequestDto.getExpirationDate())
                .monthlyPayment(registRequestDto.getMonthlyPayment())
                .isDeleted(false)
                .build();

        saveInsuranceRecordPort.save(insuranceRecord);

    }
}
