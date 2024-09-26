package com.dog.health.dogbogamserver.domain.medicalRecords.adapter.in.web;

import com.dog.health.dogbogamserver.domain.medicalRecords.application.port.in.MedicalRecordService;
import com.dog.health.dogbogamserver.domain.medicalRecords.domain.MedicalRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/medical-records")
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecord> getMedicalRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(medicalRecordService.getMedicalRecordById(id));
    }

    @PostMapping
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        MedicalRecord createdRecord = medicalRecordService.createMedicalRecord(medicalRecord);
        return ResponseEntity.ok(createdRecord);
    }

    @GetMapping("/dog/{dogId}")
    public ResponseEntity<List<MedicalRecord>> getAllMedicalRecordsForDog(@PathVariable Long dogId) {
        return ResponseEntity.ok(medicalRecordService.getAllMedicalRecordsForDog(dogId));
    }
}
