package com.hospital.appointment.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.hospital.appointment.enums.AppointmentState;
import com.hospital.appointment.enums.RejectionReason;

import lombok.Data;


@Data
@Entity
public class Appointment {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private LocalDateTime start;
	
	@Column
	private LocalDateTime end;
	
	@Enumerated(EnumType.STRING)
	private AppointmentState state=AppointmentState.PENDING;
	
	@Column(name="doctor_id")
	private Long doctorId;
	
	@Column(name="doctor_name")
	private String doctorName;
	
	@Column
	private String room;
	
	@Column
	private String clinic;
	
	@Column(name="treatment_costs")
	private Double treatmentCosts=new Double(0);
	
	@Enumerated(EnumType.STRING)
	private RejectionReason rejectionReason;
	
	@Version
    private Integer version=1;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
	Patient patient;
	
}
