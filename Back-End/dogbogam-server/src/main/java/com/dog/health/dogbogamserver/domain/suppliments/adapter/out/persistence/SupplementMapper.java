package com.dog.health.dogbogamserver.domain.suppliments.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.suppliments.domain.Supplement;
import org.springframework.stereotype.Component;

@Component
public class SupplementMapper {

    public Supplement toDomain(SupplementEntity entity) {
        return Supplement.builder()
                .supplementId(entity.getSupplementId())
                .productName(entity.getProductName())
                .brandName(entity.getBrandName())
                .target(entity.getTarget())
                .how(entity.getHow())
                .offer(entity.getOffer())
                .type(entity.getType())
                .basis(entity.getBasis())
                .protein(entity.getProtein())
                .fat(entity.getFat())
                .feature(entity.getFeature())
                .price(entity.getPrice())
                .imageName(entity.getImageName())
                .imageUrl(entity.getImageUrl())
                .build();
    }

    public SupplementEntity toEntity(Supplement domain) {
        return SupplementEntity.builder()
                .supplementId(domain.getSupplementId())
                .productName(domain.getProductName())
                .brandName(domain.getBrandName())
                .target(domain.getTarget())
                .how(domain.getHow())
                .offer(domain.getOffer())
                .type(domain.getType())
                .basis(domain.getBasis())
                .protein(domain.getProtein())
                .fat(domain.getFat())
                .feature(domain.getFeature())
                .price(domain.getPrice())
                .imageName(domain.getImageName())
                .imageUrl(domain.getImageUrl())
                .build();
    }
}
