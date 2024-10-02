package com.dog.health.dogbogamserver.domain.suppliments.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "supplement")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplement_id", nullable = false)
    private Long supplementId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "brand_name", nullable = false)
    private String brandName;

    @Column(name = "target")
    private String target;

    @Column(name = "how")
    private String how;

    @Column(name = "offer", nullable = false)
    private String offer;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "basis")
    private String basis;

    @Column(name = "protein")
    private String protein;

    @Column(name = "fat")
    private String fat;

    @Column(name = "feature")
    private String feature;

    @Column(name = "price")
    private Integer price;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_url")
    private String imageUrl;
}
