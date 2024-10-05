package com.dog.health.dogbogamserver.domain.suppliments.application.port.in;

import com.dog.health.dogbogamserver.domain.suppliments.domain.Supplement;

import java.util.List;

public interface FindSupplementUseCase {
    List<Supplement> findAllSupplements(int page, int size);
}
