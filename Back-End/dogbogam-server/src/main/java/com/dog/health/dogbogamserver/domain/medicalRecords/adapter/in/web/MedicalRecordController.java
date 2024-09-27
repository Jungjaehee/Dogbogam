package com.dog.health.dogbogamserver.domain.medicalRecords.adapter.in.web;

import com.dog.health.dogbogamserver.domain.medicalRecords.application.service.dto.request.CreateReportRequestDto;
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
    public SuccessResponse<?> createMedicalRecord(@RequestBody CreateReportRequestDto createReportRequestDto) {
        log.info("Controller Create medical record: {}", createReportRequestDto);
        createReportUseCase.createReport(createReportRequestDto);
        return SuccessResponse.created();
    }

//    @GetMapping("/dog/{dogId}")
//    public ResponseEntity<List<MedicalRecord>> getAllMedicalRecordsForDog(@PathVariable Long dogId) {
//        return ResponseEntity.ok(medicalRecordService.getAllMedicalRecordsForDog(dogId));
//    }
}
