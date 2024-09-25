package com.dog.health.dogbogamserver.domain.insurances.adapter.in.web;

import com.dog.health.dogbogamserver.domain.insurances.application.port.in.SearchInsuranceUseCase;
import com.dog.health.dogbogamserver.global.web.dto.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/insurances")
@RequiredArgsConstructor
public class InsuranceController {

    private final SearchInsuranceUseCase searchInsuranceUseCase;

    @GetMapping("/search")
    public SuccessResponse<List<?>> searchInsurance(@RequestParam("benefit") final List<String> benefit){
        return SuccessResponse.ok(searchInsuranceUseCase.search(benefit));
    }

}
