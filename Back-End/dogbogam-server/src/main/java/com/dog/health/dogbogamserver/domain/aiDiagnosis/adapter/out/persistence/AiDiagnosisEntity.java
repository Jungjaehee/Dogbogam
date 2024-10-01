package com.dog.health.dogbogamserver.domain.aiDiagnosis.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogEntity;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ai_diagnosis")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class AiDiagnosisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ai_diagnosis_id")
    private Long aiDiagnosisId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dog_id", nullable = false)
    private DogEntity dog;

    @Column(name = "normal", nullable = false)
    private Boolean normal;

    @Column(name = "diagnosis_item", nullable = false)
    private String diagnosisItem;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_url")
    private String imageUrl;
}
