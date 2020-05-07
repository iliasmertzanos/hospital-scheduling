package com.hospital.treatment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.treatment.entities.TreatmentPlan;

public interface TreatmentPlanRepo extends JpaRepository<TreatmentPlan, Long>{

}
