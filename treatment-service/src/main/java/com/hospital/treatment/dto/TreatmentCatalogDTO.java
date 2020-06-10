package com.hospital.treatment.dto;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.hospital.treatment.enums.ClinicalArea;
import com.hospital.treatment.enums.Disease;

import lombok.Data;

@Data
public class TreatmentCatalogDTO {
	
	private Long id;
	
	private ClinicalArea clinicalArea;
	
	private Double treatmentCost;
	
	private Boolean onPrivateExpences;
	
	private Disease diceaseCode;
	
	private String description;
	
	private Double duration;	
}
