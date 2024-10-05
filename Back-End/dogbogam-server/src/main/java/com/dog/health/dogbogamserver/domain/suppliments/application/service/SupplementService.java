package com.dog.health.dogbogamserver.domain.suppliments.application.service;

import com.dog.health.dogbogamserver.domain.suppliments.application.port.in.FindSupplementUseCase;
import com.dog.health.dogbogamserver.domain.suppliments.application.port.out.LoadSupplementPort;
import com.dog.health.dogbogamserver.domain.suppliments.domain.Supplement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplementService implements FindSupplementUseCase {

    private final LoadSupplementPort loadSupplementPort;

    @Override
    public List<Supplement> findAllSupplements(int page, int size) {
        return loadSupplementPort.loadAllSupplements(page, size);
    }
}
