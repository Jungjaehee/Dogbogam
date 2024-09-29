package com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.in.web;

import com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.in.dto.RegistInsuranceRecordRequestDto;
import com.dog.health.dogbogamserver.domain.insuranceRecord.adapter.in.dto.UpdateInsuranceRecordRequestDto;
import com.dog.health.dogbogamserver.domain.insuranceRecord.application.port.in.*;
import com.dog.health.dogbogamserver.global.auth.dto.MemberPrincipal;
import com.dog.health.dogbogamserver.global.web.dto.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public SuccessResponse<?> saveInsuranceRecord(@AuthenticationPrincipal MemberPrincipal memberPrincipal,
                                                  @Valid @RequestBody final RegistInsuranceRecordRequestDto registRequestDto) {
        registInsuranceRecordUseCase.registInsuranceRecord(memberPrincipal.getMemberId(), registRequestDto);

        return SuccessResponse.created();
    }

    @PatchMapping
    public SuccessResponse<?> updateInsuranceRecord(@AuthenticationPrincipal MemberPrincipal memberPrincipal,
                                                    @Valid @RequestBody final UpdateInsuranceRecordRequestDto updateRequestDto) {
        updateInsuranceRecordUseCase.updateInsuranceRecord(memberPrincipal.getMemberId(), updateRequestDto);

        return SuccessResponse.updated();
    }

    @GetMapping("/{insuranceRecordId}")
    public SuccessResponse<?> findInsuranceRecord(@AuthenticationPrincipal MemberPrincipal memberPrincipal,
                                                  @PathVariable("insuranceRecordId") Long insuranceRecordId) {
        return SuccessResponse.ok(findInsuranceRecordUseCase.findInsuranceRecordById(memberPrincipal.getMemberId(), insuranceRecordId));
    }


    @DeleteMapping("/{insuranceRecordId}")
    public SuccessResponse<?> deleteInsuranceRecord(@AuthenticationPrincipal MemberPrincipal memberPrincipal,
                                                    @PathVariable("insuranceRecordId")Long insuranceRecordId) {
        deleteInsuranceRecordUseCase.deleteInsuranceRecord(memberPrincipal.getMemberId(), insuranceRecordId);

        return SuccessResponse.deleted();
    }

    @GetMapping("all/{dogId}")
    public SuccessResponse<?> findAllInsuranceRecord(@AuthenticationPrincipal MemberPrincipal memberPrincipal,
                                                     @PathVariable("dogId") Long dogId,
                                                     @RequestParam(value = "size", defaultValue = "5") int size,
                                                     @RequestParam(value = "page", defaultValue = "1") int page) {
        return SuccessResponse.ok(findAllInsuranceRecordUseCase.findAllInsuranceRecord(memberPrincipal.getMemberId(), dogId, size, page));
    }
}
