package com.dog.health.dogbogamserver.domain.insurances.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.insurances.domain.InsuranceBenefit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InsuranceBenefitSpringDataRepository extends JpaRepository<InsuranceBenefitEntity, Long> {

    @Query("SELECT DISTINCT ib.insurance FROM InsuranceBenefitEntity ib WHERE ib.benefit IN :benefits")
    List<InsuranceEntity> findInsuranceEntityByBenefitIn(List<String> benefits);

    @Query("SELECT ib.benefit FROM InsuranceBenefitEntity ib WHERE ib.insurance.insuranceId = :insuranceId")
    List<String> findBenefitByInsuranceId(Long insuranceId);



    List<InsuranceBenefitEntity> findDistinctBenefitBy();
}
