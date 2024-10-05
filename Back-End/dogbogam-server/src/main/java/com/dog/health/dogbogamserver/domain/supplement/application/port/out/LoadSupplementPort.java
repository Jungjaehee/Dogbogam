package com.dog.health.dogbogamserver.domain.supplement.application.port.out;

import com.dog.health.dogbogamserver.domain.supplement.domain.Supplement;

import java.util.List;
import java.util.Optional;

public interface LoadSupplementPort {
    List<Supplement> loadAllSupplements(int page, int size);
    Optional<Supplement> findSupplementById(Long supplementId);
}
