package com.dog.health.dogbogamserver.domain.medicalRecords.adapter.in.web;

import com.dog.health.dogbogamserver.domain.medicalRecords.adapter.in.dto.CreateReportDto;
import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in.CreateReportUseCase;
import com.dog.health.dogbogamserver.global.web.dto.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/medical-records")
public class MedicalRecordController {

    private final CreateReportUseCase createReportUseCase;

//    @GetMapping("/{id}")
//    public ResponseEntity<MedicalRecord> getMedicalRecordById(@PathVariable Long id) {
//        return ResponseEntity.ok(medicalRecordService.getMedicalRecordById(id));
//    }

    @PostMapping
    public SuccessResponse<?> createMedicalRecord(@RequestBody CreateReportDto createReportDto) {
        log.info("Controller Create medical record: {}", createReportDto);
        createReportUseCase.createReport(createReportDto);
        return SuccessResponse.ok();
    }

//    @GetMapping("/dog/{dogId}")
//    public ResponseEntity<List<MedicalRecord>> getAllMedicalRecordsForDog(@PathVariable Long dogId) {
//        return ResponseEntity.ok(medicalRecordService.getAllMedicalRecordsForDog(dogId));
//    }
}
