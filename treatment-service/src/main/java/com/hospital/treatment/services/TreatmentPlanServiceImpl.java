package com.hospital.treatment.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hospital.treatment.dto.TreatmentPlanDTO;
import com.hospital.treatment.entities.Doctor;
import com.hospital.treatment.entities.TreatmentCatalog;
import com.hospital.treatment.entities.TreatmentPlan;
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

	@Override
	@Transactional
	public TreatmentPlan saveNewTreatmentPlan(Doctor doctor, Date date, TreatmentCatalog treatmentCatalog, Long patientId) {
		TreatmentPlan myNewTreatmentPlan=new TreatmentPlan();
		myNewTreatmentPlan.setDate(date);
		myNewTreatmentPlan.setDoctor(doctor);
		myNewTreatmentPlan.setTreatmentCatalog(treatmentCatalog);
		myNewTreatmentPlan.setPatientId(patientId);
		return myTMRepo.save(myNewTreatmentPlan);
	}

}
