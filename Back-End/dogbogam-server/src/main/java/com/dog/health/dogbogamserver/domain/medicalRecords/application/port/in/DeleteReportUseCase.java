package com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in;

import java.io.IOException;

public interface DeleteReportUseCase {
    void deleteReportUseCase(Long reportId) throws IOException;
}
