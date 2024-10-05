package com.dog.health.dogbogamserver.domain.suppliments.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.suppliments.application.port.out.LoadSupplementPort;
import com.dog.health.dogbogamserver.domain.suppliments.domain.Supplement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SupplementPersistenceAdapter implements LoadSupplementPort {

    private final SupplementSpringDataRepository repository;
    private final SupplementMapper mapper;

    @Override
    public List<Supplement> loadAllSupplements(int page, int size) {
        return repository.findAll(PageRequest.of(page, size))
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
