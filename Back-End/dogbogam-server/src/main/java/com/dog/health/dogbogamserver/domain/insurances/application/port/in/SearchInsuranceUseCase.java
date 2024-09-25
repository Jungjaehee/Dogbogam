package com.dog.health.dogbogamserver.domain.insurances.application.port.in;

import com.dog.health.dogbogamserver.domain.insurances.domain.Insurance;

import java.util.List;
import java.util.Map;

public interface SearchInsuranceUseCase {

    List<Map<String, Object>> search(List<String> benefit);

}
