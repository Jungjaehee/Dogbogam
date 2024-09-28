package com.dog.health.dogbogamserver.domain.insuranceRecord.application.service;

import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.in.dto.InsuranceRecordResponseDto;
import com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.in.dto.RegistInsuranceRecordRequestDto;
import com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.in.dto.UpdateInsuranceRecordRequestDto;
import com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.in.*;
import com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.out.PageableLoadInsuranceRecordPort;
import com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.out.SaveInsuranceRecordPort;
import com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.out.LoadInsuranceRecordPort;
import com.dog.health.dogbogamserver.domain.insuranceRecord.domain.InsuranceRecord;
import com.dog.health.dogbogamserver.domain.insurance.domain.Insurance;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class InsuranceRecordService implements RegistInsuranceRecordUseCase, UpdateInsuranceRecordUseCase
        , FindInsuranceRecordUseCase, DeleteInsuranceRecordUseCase, FindAllInsuranceRecordUseCase {

    private final SaveInsuranceRecordPort saveInsuranceRecordPort;
    private final LoadInsuranceRecordPort loadInsuranceRecordPort;
    private final PageableLoadInsuranceRecordPort pageableLoadInsuranceRecordPort;

    @Override
    public void registInsuranceRecord(Long memberId, RegistInsuranceRecordRequestDto registRequestDto){
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
        Dog dog = saveInsuranceRecordPort.checkExistingDog(registRequestDto.getDogId())
                .orElseThrow(() -> new IllegalArgumentException("해당 반려견이 존재하지 않습니다."));

        // 4. 해당 반려견의 보호자가 로그인한 멤버인지 확인
        if(dog.getMember().getMemberId() != memberId)
            throw new IllegalArgumentException("해당 반려견에 대한 접근 권한이 존재하지 않습니다.");

        // 5. 해당 정보 저장
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

    @Override
    public void updateInsuranceRecord(Long memberId, UpdateInsuranceRecordRequestDto updateRequestDto){
        // 1. 해당 펫이 해당 보험을 등록했는지 검사
        InsuranceRecord existInsuranceRecord = loadInsuranceRecordPort.loadInsuranceRecord(updateRequestDto.getInsuranceRecordId())
                .orElseThrow(() -> new IllegalArgumentException("해당 보험 기록이 존재하지 않습니다."));

        // 2. 해당 반려견의 보호자가 로그인한 멤버인지 확인
        if(existInsuranceRecord.getDog().getMember().getMemberId() != memberId)
            throw new IllegalArgumentException("해당 반려견에 대한 접근 권한이 존재하지 않습니다.");

        // 3. 수정
        InsuranceRecord insuranceRecord = InsuranceRecord.builder()
                .insuranceRecordId(updateRequestDto.getInsuranceRecordId())
                .insurance(existInsuranceRecord.getInsurance())
                .dog(existInsuranceRecord.getDog())
                .registDate(updateRequestDto.getRegistDate())
                .expirationDate(updateRequestDto.getExpirationDate())
                .monthlyPayment(updateRequestDto.getMonthlyPayment())
                .isDeleted(false)
                .build();

        saveInsuranceRecordPort.save(insuranceRecord);
    }

    @Override
    public InsuranceRecordResponseDto findInsuranceRecordById(Long memberId, Long insuranceRecordId){
        // 1. 해당 정보 조회
        InsuranceRecord insuranceRecord = loadInsuranceRecordPort.loadInsuranceRecord(insuranceRecordId)
                .orElseThrow(() -> new IllegalArgumentException("해당 보험 기록이 존재하지 않습니다."));
        
        // 2. 접근 권한 확인
        if(insuranceRecord.getDog().getMember().getMemberId() != memberId)
            throw new IllegalArgumentException("해당 보험 가입 정보에 대한 접근 권한이 존재하지 않습니다.");
        
        // 3. 결과 처리
        InsuranceRecordResponseDto responseDto = new InsuranceRecordResponseDto(insuranceRecord.getInsuranceRecordId(),
                insuranceRecord.getDog().getDogId(), insuranceRecord.getDog().getName(),
                insuranceRecord.getInsurance().getInsuranceId(), insuranceRecord.getInsurance().getName(),
                insuranceRecord.getInsurance().getCompany(), insuranceRecord.getInsurance().getS3ImageUrl(),
                insuranceRecord.getRegistDate(), insuranceRecord.getMonthlyPayment(), insuranceRecord.getExpirationDate());

        return responseDto;
    }

    @Override
    public void deleteInsuranceRecord(Long memberId, Long insuranceRecordId){
        // 1. 해당 보험 기록 존재 확인
        InsuranceRecord insuranceRecord = loadInsuranceRecordPort.loadInsuranceRecord(insuranceRecordId)
                .orElseThrow(() -> new IllegalArgumentException("해당 보험 기록이 존재하지 않습니다."));

        // 2. 접근 권한 확인
        if(insuranceRecord.getDog().getMember().getMemberId() != memberId)
            throw new IllegalArgumentException("해당 보험 가입 정보에 대한 접근 권한이 존재하지 않습니다.");
        
        // 3. 논리 삭제
        insuranceRecord.delete();

        // 4. DB 수정
        saveInsuranceRecordPort.save(insuranceRecord);
    }

    @Override
    public Map<String, Object> findAllInsuranceRecord(Long memberId, Long dogId, int size, int page){
        // 1. 반려견 존재 여부 검사
        Dog dog = saveInsuranceRecordPort.checkExistingDog(dogId)
                .orElseThrow(() -> new IllegalArgumentException("해당 반려견이 존재하지 않습니다."));

        // 2. 해당 반려견에 대한 접근 권한 확인
        if(dog.getMember().getMemberId() != memberId)
            throw new IllegalArgumentException("해당 반려견에 대한 접근 권한이 존재하지 않습니다.");

        // 3. 리스트 조회
        Page<InsuranceRecord> insuranceRecordPage = pageableLoadInsuranceRecordPort.findAllInsuranceRecords(dogId, size, page);

        // 4. 결과 처리
        Map<String, Object> result = new HashMap<>();

        List<InsuranceRecordResponseDto> insuranceRecordResponseDtoList = new ArrayList<>();
        for (InsuranceRecord insuranceRecord : insuranceRecordPage.getContent()) {
            InsuranceRecordResponseDto responseDto = new InsuranceRecordResponseDto(insuranceRecord.getInsuranceRecordId(),
                    insuranceRecord.getDog().getDogId(), insuranceRecord.getDog().getName(),
                    insuranceRecord.getInsurance().getInsuranceId(), insuranceRecord.getInsurance().getName(),
                    insuranceRecord.getInsurance().getCompany(), insuranceRecord.getInsurance().getS3ImageUrl(),
                    insuranceRecord.getRegistDate(), insuranceRecord.getMonthlyPayment(), insuranceRecord.getExpirationDate());

            insuranceRecordResponseDtoList.add(responseDto);
        }

        result.put("totalElements", insuranceRecordPage.getTotalElements());
        result.put("totalPages", insuranceRecordPage.getTotalPages());
        result.put("size", insuranceRecordPage.getNumberOfElements());
        result.put("page", insuranceRecordPage.getNumber()+1);
        result.put("insuranceRecords", insuranceRecordResponseDtoList);

        return result;
    }
}
