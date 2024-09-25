package com.dog.health.dogbogamserver.domain.insurances.application.service;

import com.dog.health.dogbogamserver.domain.insurances.application.port.in.FindDetailInsuranceUseCase;
import com.dog.health.dogbogamserver.domain.insurances.application.port.in.SearchInsuranceUseCase;
import com.dog.health.dogbogamserver.domain.insurances.application.port.out.FindDetailInsurancePort;
import com.dog.health.dogbogamserver.domain.insurances.application.port.out.SearchInsurancePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class InsuranceBenefitService implements SearchInsuranceUseCase, FindDetailInsuranceUseCase {

    private final FindDetailInsurancePort findDetailInsurancePort;
    private final SearchInsurancePort insurancePort;

    @Override
    public List<Map<String, Object>> search(List<String> benefit) {
        log.info("Insurance search started : ", benefit.toString());
        if(benefit.size() == 0)
            throw new IllegalArgumentException("선택된 보장 혜택이 존재하지 않습니다.");

        return insurancePort.findByBenefit(benefit);
    }

    @Override
    public Map<String, Object> findDetailByInsuranceId(Long insuranceId){
        return findDetailInsurancePort.findByInsuranceId(insuranceId);
    }
}
