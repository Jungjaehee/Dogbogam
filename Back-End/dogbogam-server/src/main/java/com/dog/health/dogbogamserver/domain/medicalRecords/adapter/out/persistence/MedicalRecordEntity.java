package com.dog.health.dogbogamserver.domain.medicalRecords.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogEntity;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.UpdateReportRequestDto;
import com.dog.health.dogbogamserver.domain.medicalRecords.domain.MedicalRecord;
import com.dog.health.dogbogamserver.global.baseTimeEntity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Table(name = "medical_record")
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
@Entity
public class MedicalRecordEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_record_id", nullable = false)
    private Long medicalRecordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dog_id", nullable = false)
    private DogEntity dog;

    @Column(name = "record_time", nullable = false)
    private LocalDateTime recordTime;

    @Column(name = "content", columnDefinition = "TEXT", nullable = true)
    private String content;

    @Column(name = "hospital", nullable = false)
    private String hospital;

    @Column(name = "image_name", nullable = true)
    private String imageName;

    @Column(name = "image_url", nullable = true)
    private String imageUrl;

    @Column(name = "cost", nullable = true)
    private Long cost;

    public void update(MedicalRecord medicalRecord) {
        recordTime = medicalRecord.getRecordTime();
        content = medicalRecord.getContent();
        hospital = medicalRecord.getHospital();
    }
}
