package com.dog.health.dogbogamserver.domain.suppliments.application.port.out;

import com.dog.health.dogbogamserver.domain.suppliments.domain.Supplement;

import java.util.List;

public interface LoadSupplementPort {
    List<Supplement> loadAllSupplements(int page, int size);
}
