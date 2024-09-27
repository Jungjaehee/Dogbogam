package com.dog.health.dogbogamserver.domain.insurance.application.port.in;

import java.util.List;
import java.util.Map;

public interface SearchInsuranceUseCase {

    Map<Long, Map<String, Object>> search(List<String> benefit);

}
