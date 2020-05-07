package com.hospital.treatment.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.hospital.treatment.enums.ClinicalArea;

import lombok.Data;

@Data
@Entity
public class Doctor {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="working_hours")
	private Double workingHours;
	
	@Column
	private String spezialisation;	
	
	@Enumerated(EnumType.STRING)
	private ClinicalArea clinicalArea;
	
	@OneToMany(
			fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "doctor"
    )
	private List<TreatmentPlan> myTreatmentPlans;

}
