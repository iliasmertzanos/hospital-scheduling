package com.hospital.appointment.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hospital.appointment.enums.Disease;

import lombok.Builder;
import lombok.Data;

@Data
//@Builder
public class PatientDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1903756815218306498L;

	private Long id;
	
	private String name;
	
	private Integer age;
	
	private Double weight;
	
	private Double height;	
	
	private Long insuranceId;
	
	private Disease disease;
	
	private boolean financialApproved;
	
}
