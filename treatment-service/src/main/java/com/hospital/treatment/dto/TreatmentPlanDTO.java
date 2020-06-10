package com.hospital.treatment.dto;

import java.util.Date;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.hospital.treatment.entities.Doctor;
import com.hospital.treatment.entities.TreatmentCatalog;

import lombok.Data;

@Data
public class TreatmentPlanDTO {
	
	private Long id;
	
	private Date date;
	
	private TreatmentCatalog treatmentCatalog;
	
	private Doctor doctor;
}
