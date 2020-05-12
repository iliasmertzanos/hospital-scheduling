package com.hospital.appointment.dto;

import java.io.Serializable;
import java.sql.Date;
import com.hospital.appointment.enums.AppointmentState;

import lombok.Data;

@Data
public class AppointmentDTO implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4745110408394644242L;

	private Long id;
	
	private Date start;
	
	private AppointmentState state=AppointmentState.PENDING;
	
	private Long doctorId;
	
	private String doctorName;
	
	private String room;
	
	private String clinic;
	
	private Double treatmentCosts;
	
	private String rejectionReason;
	
    private Integer version;
	
	PatientDTO patient;
}
