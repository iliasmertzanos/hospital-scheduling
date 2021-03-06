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
import com.hospital.treatment.enums.Disease;
import com.hospital.treatment.enums.PlanPeriod;
import com.hospital.treatment.enums.RejectionReason;

import lombok.Data;

@Data
public class AppointmentPayloadDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9162498142485938484L;

	private Long id;
	
	private Long doctorId;
	
	private String doctorName;
	
	private String clinic;
	
	private Double treatmentCosts;
	
    private Integer version;
    
    private String disease;
    
    private String rejectionReason;
    
    private Date date;
    
    private Boolean onPrivateExpences;
    
    private Long patientId;
}
