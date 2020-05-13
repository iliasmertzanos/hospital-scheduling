package com.hospital.payloads;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import org.hibernate.engine.jdbc.SerializableClobProxy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hospital.appointment.entities.Patient;
import com.hospital.appointment.enums.AppointmentState;
import com.hospital.appointment.enums.Disease;
import com.hospital.appointment.enums.ExecutionEventPoint;
import com.hospital.appointment.enums.RejectionReason;

import lombok.Builder;
import lombok.Data;

@Data
public class AppointmentPayloadDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9162498142485938484L;

	private Long id;
	
	private Date start;
	
	private Long doctorId;
	
	private String doctorName;
	
	private String room;
	
	private String clinic;
	
	private Double treatmentCosts;
	
	private String rejectionReason;
	
    private Integer version;
    
    private String disease;
    
    private Long patientId;
}
