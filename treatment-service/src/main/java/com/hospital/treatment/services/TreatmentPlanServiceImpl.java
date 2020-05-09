package com.hospital.treatment.services;

import org.springframework.stereotype.Service;

import com.hospital.treatment.dto.TreatmentPlanDTO;
import com.hospital.treatment.repositories.TreatmentPlanRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RequiredArgsConstructor
public class TreatmentPlanServiceImpl implements TreatmentPlanService {
	
	private final TreatmentPlanRepo myTMRepo;
	
	public TreatmentPlanDTO saveNewTreatmentPlan(TreatmentPlanDTO newTreatmentPlan) {
		return newTreatmentPlan;
		
	}

}
