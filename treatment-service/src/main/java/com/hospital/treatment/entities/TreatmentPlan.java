package com.hospital.treatment.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class TreatmentPlan {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Date date;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tc_id")
	private TreatmentCatalog treatmentCatalog;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
	private Doctor doctor;

}
