package com.dog.health.dogbogamserver.domain.medicalRecords.application.port.out;

import com.dog.health.dogbogamserver.domain.medicalRecords.adapter.out.persistence.MedicalRecordEntity;

public interface UpdateReportPort {
    void updateReport(MedicalRecordEntity medicalRecordEntity);
}