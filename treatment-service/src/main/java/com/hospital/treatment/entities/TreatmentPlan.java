package com.hospital.treatment.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class TreatmentPlan {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column
	private Long patientId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tc_id")
	private TreatmentCatalog treatmentCatalog;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
	private Doctor doctor;

}
