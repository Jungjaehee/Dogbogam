package com.dog.health.dogbogamserver.domain.insurance.application.service;

import com.dog.health.dogbogamserver.domain.insurance.adapter.in.dto.DiagnosisItem;
import com.dog.health.dogbogamserver.domain.insurance.adapter.in.dto.RecommendInsuranceResponseDto;
import com.dog.health.dogbogamserver.domain.insurance.application.port.in.*;

import com.dog.health.dogbogamserver.domain.insurance.application.port.out.*;
import com.dog.health.dogbogamserver.domain.insurance.domain.Insurance;
import com.dog.health.dogbogamserver.domain.insurance.domain.InsuranceBenefit;
import com.dog.health.dogbogamserver.global.web.exception.CustomException;
import com.dog.health.dogbogamserver.global.web.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class InsuranceService implements FindAllInsuranceUseCase, FindDetailInsuranceUseCase, SearchInsuranceUseCase,
        FindAllInsuranceBenefitUseCase, RecommendInsuranceUseCase {

    private final FindDetailInsurancePort findDetailInsurancePort;
    private final FindAllInsurancePort findAllInsurancePort;
    private final SearchInsurancePort searchInsurancePort;
    private final FindAllInsuranceBenefitPort findAllInsuranceBenefitPort;
    private final RecommendInsurancePort recommendInsurancePort;

    @Override
    public Map<Long, Map<String, Object>> findAll() {
        List<InsuranceBenefit> insuranceBenefits = findAllInsurancePort.findAllInsurance();

        return benefitListToInsuranceInfoList(insuranceBenefits);
    }

    @Override
    public Map<Long, Map<String, Object>> findDetailByInsuranceId(Long insuranceId){
        findDetailInsurancePort.existInsuranceById(insuranceId)
                .orElseThrow(()-> new CustomException(ErrorCode.INSURANCE_NOT_FOUND));

        List<InsuranceBenefit> insuranceBenefits = findDetailInsurancePort.findByInsuranceId(insuranceId);
        return benefitListToInsuranceInfoList(insuranceBenefits);
    }

    @Override
    public Map<Long, Map<String, Object>> search(List<String> benefit) {
        if(benefit.isEmpty())
            throw new CustomException(ErrorCode.INSURANCE_BENEFIT_NOT_FOUND);

        List<InsuranceBenefit> insuranceBenefits = searchInsurancePort.findByBenefit(benefit);

        return benefitListToInsuranceInfoList(insuranceBenefits);
    }

    @Override
    public List<String> findAllBenefits(){
        List<InsuranceBenefit> insuranceBenefits = findAllInsuranceBenefitPort.findAllBenefits();
        Set<String> benefitSet = new HashSet<>(insuranceBenefits.size());

        for(InsuranceBenefit benefit : insuranceBenefits){
            benefitSet.add(benefit.getBenefit());
        }

        return new ArrayList<>(benefitSet);
    }

    @Override
    public RecommendInsuranceResponseDto recommendInsurance(DiagnosisItem diagnosisItem){

        List<InsuranceBenefit> insurances = recommendInsurancePort.findByBenefitAndAscFee(diagnosisItem.getBenefitCategory());

        if(insurances.isEmpty()) throw new CustomException(ErrorCode.INSURANCE_RECOMMEND_NOT_FOUND);

        int randomInt = (int) (Math.random() * insurances.size());
        Insurance bestInsurance = insurances.get(randomInt).getInsurance();

        RecommendInsuranceResponseDto responseDto = new RecommendInsuranceResponseDto(
                bestInsurance.getInsuranceId(),
                bestInsurance.getName(),
                bestInsurance.getCompany(),
                bestInsurance.getS3ImageUrl(),
                bestInsurance.getFee()
        );

        return responseDto;
    }

    private Map<Long, Map<String, Object>> benefitListToInsuranceInfoList(List<InsuranceBenefit> insuranceBenefitList){
        Map<Long, Map<String, Object>> insuranceBenfitMap = new HashMap();

        for(InsuranceBenefit benefitDomain : insuranceBenefitList){
            Long key = benefitDomain.getInsurance().getInsuranceId();
            if(!insuranceBenfitMap.containsKey(key)){
                insuranceBenfitMap.put(key, new HashMap<>());
                insuranceBenfitMap.get(key).put("insurance", benefitDomain.getInsurance());
                insuranceBenfitMap.get(key).put("benefit", new ArrayList<String>());
            }

            List<String> benefits = (List<String>) insuranceBenfitMap.get(key).get("benefit");
            benefits.add(benefitDomain.getBenefit());

        }

        return insuranceBenfitMap;

    }

}
