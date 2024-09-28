package com.dog.health.dogbogamserver.domain.insurance.adapter.in.web;

import com.dog.health.dogbogamserver.domain.insurance.application.port.in.FindAllInsuranceBenefitUseCase;
import com.dog.health.dogbogamserver.domain.insurance.application.port.in.FindAllInsuranceUseCase;
import com.dog.health.dogbogamserver.domain.insurance.application.port.in.FindDetailInsuranceUseCase;
import com.dog.health.dogbogamserver.domain.insurance.application.port.in.SearchInsuranceUseCase;
import com.dog.health.dogbogamserver.global.web.dto.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insurances")
@RequiredArgsConstructor
public class InsuranceController {

    private final SearchInsuranceUseCase searchInsuranceUseCase;
    private final FindAllInsuranceUseCase findAllInsuranceUseCase;
    private final FindAllInsuranceBenefitUseCase findAllInsuranceBenefitUseCase;
    private final FindDetailInsuranceUseCase findDetailInsuranceUseCase;

    @GetMapping("/search")
    public SuccessResponse<?> searchInsurance(@RequestParam("benefit") final List<String> benefit){
        return SuccessResponse.ok(searchInsuranceUseCase.search(benefit));
    }

    @GetMapping
    public SuccessResponse<?> findAllInsurances(){
        return SuccessResponse.ok(findAllInsuranceUseCase.findAll());
    }

    @GetMapping("/benefit")
    public SuccessResponse<?> findAllInsuranceBenefits(){
        return SuccessResponse.ok(findAllInsuranceBenefitUseCase.findAllBenefits());
    }

    @GetMapping("/{insuranceId}")
    public SuccessResponse<?> findDetailInsuranceById(@PathVariable("insuranceId") final Long insuranceId){
        return SuccessResponse.ok(findDetailInsuranceUseCase.findDetailByInsuranceId(insuranceId));
    }
}
