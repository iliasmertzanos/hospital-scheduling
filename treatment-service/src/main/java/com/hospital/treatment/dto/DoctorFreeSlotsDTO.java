package com.hospital.treatment.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DoctorFreeSlotsDTO {
	private Double restHours;
	private Date slotDate;
	private Long doctorId;
	
}
