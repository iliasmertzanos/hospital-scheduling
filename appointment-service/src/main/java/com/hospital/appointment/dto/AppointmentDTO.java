package com.hospital.appointment.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.engine.jdbc.SerializableClobProxy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hospital.appointment.enums.AppointmentState;
import com.hospital.appointment.enums.RejectionReason;
import com.hospital.appointment.models.Patient;

import lombok.Data;

@Data
public class AppointmentDTO implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4745110408394644242L;

	private Long id;
	
	private LocalDateTime start;
	
	private LocalDateTime end;
	
	private AppointmentState state=AppointmentState.PENDING;
	
	private Long doctorId;
	
	private String doctorName;
	
	private String room;
	
	private String clinic;
	
	private Double treatmentCosts;
	
	private RejectionReason rejectionReason;
	
    private Integer version;
	
	PatientDTO patient;
}
