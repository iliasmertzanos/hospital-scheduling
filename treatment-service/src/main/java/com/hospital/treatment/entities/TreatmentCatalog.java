package com.hospital.treatment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.hospital.treatment.enums.ClinicalArea;
import com.hospital.treatment.enums.Dicease;

import lombok.Data;

@Data
@Entity
public class TreatmentCatalog {
	
	@Id
	@GeneratedValue
	@Column(name="tc_id")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private ClinicalArea clinicalArea;
	
	@Column(name="treatment_cost")
	private Double treatmentCost;
	
	@Column(name="on_private_expences")
	private Boolean onPrivateExpences;
	
	@Column(name="dicease_code")
	private Dicease diceaseCode;
	
	@Column
	private String description;
	
	private Double duration;	

}
