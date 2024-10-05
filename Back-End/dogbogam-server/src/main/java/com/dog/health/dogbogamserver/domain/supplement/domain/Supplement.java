package com.dog.health.dogbogamserver.domain.supplement.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Supplement {
    private Long supplementId;
    private String productName;
    private String brandName;
    private String target;
    private String how;
    private String offer;
    private String type;
    private String basis;
    private String protein;
    private String fat;
    private String feature;
    private Integer price;
    private String imageName;
    private String imageUrl;

    @Builder
    public Supplement(Long supplementId, String productName, String brandName, String target, String how, String offer, String type, String basis, String protein, String fat, String feature, Integer price, String imageName, String imageUrl) {
        this.supplementId = supplementId;
        this.productName = productName;
        this.brandName = brandName;
        this.target = target;
        this.how = how;
        this.offer = offer;
        this.type = type;
        this.basis = basis;
        this.protein = protein;
        this.fat = fat;
        this.feature = feature;
        this.price = price;
        this.imageName = imageName;
        this.imageUrl = imageUrl;
    }
}
