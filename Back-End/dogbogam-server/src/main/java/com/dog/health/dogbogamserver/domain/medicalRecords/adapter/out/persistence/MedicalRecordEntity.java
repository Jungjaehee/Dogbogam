package com.dog.health.dogbogamserver.domain.medicalRecords.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.dogs.adapter.out.persistence.DogEntity;
import com.dog.health.dogbogamserver.global.baseTimeEntity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "medical_record")
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
public class MedicalRecordEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_record_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dog_id", nullable = false)
    private DogEntity dog;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "content", columnDefinition = "TEXT", nullable = true)
    private String content;

    @Column(name = "hospital", nullable = false)
    private String hospital;

    @Column(name = "image_name", nullable = true)
    private String imageName;

    @Column(name = "image_url", nullable = true)
    private String imageUrl;
}
