package com.hospital.treatment.dto;

import java.util.List;

import com.hospital.treatment.entities.TreatmentPlan;
import com.hospital.treatment.enums.ClinicalArea;

import lombok.Data;

@Data
public class DoctorDTO {
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private Double workingHours;
	
	private String spezialisation;	
	
	private ClinicalArea clinicalArea;
	
	private List<TreatmentPlan> myTreatmentPlans;
	
}
