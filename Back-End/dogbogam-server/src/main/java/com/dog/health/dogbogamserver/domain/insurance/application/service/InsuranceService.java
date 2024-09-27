package com.dog.health.dogbogamserver.domain.insurance.application.service;

import com.dog.health.dogbogamserver.domain.insurance.application.port.in.FindAllInsuranceUseCase;

import com.dog.health.dogbogamserver.domain.insurance.application.port.in.FindDetailInsuranceUseCase;
import com.dog.health.dogbogamserver.domain.insurance.application.port.out.FindAllInsurancePort;
import com.dog.health.dogbogamserver.domain.insurance.application.port.out.FindDetailInsurancePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class InsuranceService implements FindAllInsuranceUseCase, FindDetailInsuranceUseCase {

    private final FindDetailInsurancePort findDetailInsurancePort;
    private final FindAllInsurancePort findAllInsurancePort;

    @Override
    public List<Map<String, Object>> findAll() {
        return findAllInsurancePort.findAll();
    }

    @Override
    public Map<String, Object> findDetailByInsuranceId(Long insuranceId){
        return findDetailInsurancePort.findByInsuranceId(insuranceId);
    }
}
