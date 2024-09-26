package com.dog.health.dogbogamserver.domain.insurances.application.service;

import com.dog.health.dogbogamserver.domain.insurances.application.port.in.FindAllInsuranceUseCase;

import com.dog.health.dogbogamserver.domain.insurances.application.port.out.FindAllInsurancePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class InsuranceService implements FindAllInsuranceUseCase {

    private final FindAllInsurancePort findAllInsurancePort;

    @Override
    public List<Map<String, Object>> findAll() {
        return findAllInsurancePort.findAll();
    }

}
