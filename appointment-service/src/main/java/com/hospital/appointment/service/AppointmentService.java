package com.hospital.appointment.service;

import com.hospital.appointment.dto.AppointmentDTO;
import com.hospital.appointment.dto.AppointmentPayloadDTO;
import com.hospital.appointment.dto.PatientDTO;
import com.hospital.appointment.enums.Dicease;

public interface AppointmentService {

	AppointmentDTO handleNewIncomingAppointment(AppointmentDTO myPayload);

	AppointmentDTO handleFinancialResultReception(AppointmentDTO myPayload);
	
	AppointmentDTO handleFinancialResultFailure(AppointmentDTO myPayload);
	
	AppointmentDTO handleTreatmentResultReception(AppointmentDTO myPayload);	
	
	AppointmentDTO handleTreatmentResultFailure(AppointmentDTO myPayload);
	
	AppointmentDTO getAppointment(Long Id);

}
