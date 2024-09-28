package com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.in.web;

import com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.in.dto.RegistInsuranceRecordRequestDto;
import com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.in.dto.UpdateInsuranceRecordRequestDto;
import com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.in.*;
import com.dog.health.dogbogamserver.global.web.dto.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("insurance-records")
public class InsuranceRecordController {

    private final RegistInsuranceRecordUseCase registInsuranceRecordUseCase;
    private final UpdateInsuranceRecordUseCase updateInsuranceRecordUseCase;
    private final FindInsuranceRecordUseCase findInsuranceRecordUseCase;
    private final DeleteInsuranceRecordUseCase deleteInsuranceRecordUseCase;
    private final FindAllInsuranceRecordUseCase findAllInsuranceRecordUseCase;

    @PostMapping
    public SuccessResponse<?> saveInsuranceRecord(@RequestBody final RegistInsuranceRecordRequestDto registRequestDto) {
        registInsuranceRecordUseCase.registInsuranceRecord(registRequestDto);

        return SuccessResponse.created();
    }

    @PatchMapping
    public SuccessResponse<?> updateInsuranceRecord(@RequestBody final UpdateInsuranceRecordRequestDto updateRequestDto) {
        updateInsuranceRecordUseCase.updateInsuranceRecord(updateRequestDto);

        return SuccessResponse.updated();
    }

    @GetMapping("/{insuranceRecordId}")
    public SuccessResponse<?> findInsuranceRecord(@PathVariable("insuranceRecordId") Long insuranceRecordId) {
        return SuccessResponse.ok(findInsuranceRecordUseCase.findInsuranceRecordById(insuranceRecordId));
    }


    @DeleteMapping("/{insuranceRecordId}")
    public SuccessResponse<?> deleteInsuranceRecord(@PathVariable("insuranceRecordId")Long insuranceRecordId) {
        deleteInsuranceRecordUseCase.deleteInsuranceRecord(insuranceRecordId);

        return SuccessResponse.deleted();
    }

    @GetMapping("all/{dogId}")
    public SuccessResponse<?> findAllInsuranceRecord(@PathVariable("dogId") Long dogId,
                                                     @RequestParam(value = "size", defaultValue = "5") int size,
                                                     @RequestParam(value = "page", defaultValue = "1") int page) {
        return SuccessResponse.ok(findAllInsuranceRecordUseCase.findAllInsuranceRecord(dogId, size, page));
    }
}
