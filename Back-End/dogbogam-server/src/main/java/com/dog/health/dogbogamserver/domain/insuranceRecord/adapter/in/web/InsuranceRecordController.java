package com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.in.web;

import com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.in.dto.RegistRequestDto;
import com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.in.dto.UpdateRequestDto;
import com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.in.DeleteInsuranceRecordUseCase;
import com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.in.FindInsuranceRecordUseCase;
import com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.in.RegistInsuranceRecordUseCase;
import com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.in.UpdateInsuranceRecordUseCase;
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

    @PostMapping
    public SuccessResponse<?> saveInsuranceRecord(@RequestBody final RegistRequestDto registRequestDto) {
        registInsuranceRecordUseCase.registInsuranceRecord(registRequestDto);

        return SuccessResponse.created();
    }

    @PatchMapping
    public SuccessResponse<?> updateInsuranceRecord(@RequestBody final UpdateRequestDto updateRequestDto) {
        updateInsuranceRecordUseCase.updateInsuranceRecord(updateRequestDto);

        return SuccessResponse.updated();
    }

    @GetMapping("/{insuranceRecordId}")
    public SuccessResponse<?> findInsuranceRecord(@PathVariable("insuranceRecordId") Long insuranceRecordId) {
        return SuccessResponse.ok(findInsuranceRecordUseCase.findInsuranceRecordById(insuranceRecordId));
    }


    @DeleteMapping("/{insuranceRecordId")
    public SuccessResponse<?> deleteInsuranceRecord(@PathVariable("insuranceRecordId")Long insuranceRecordId) {
        deleteInsuranceRecordUseCase.deleteInsuranceRecord(insuranceRecordId);

        return SuccessResponse.deleted();
    }
}
