package com.dog.health.dogbogamserver.domain.medicalRecords.adapter.out.persistence;

import jakarta.persistence.*;
import com.dog.health.dogbogamserver.global.baseTimeEntity.BaseTimeEntity;
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
    private Long id;

    private Long dogId;
    private LocalDateTime date;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String hospital;
    private String imageName;
    private String imageUrl;
}
