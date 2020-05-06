package com.hospital.appointment.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.appointment.dto.AppointmentDTO;
import com.hospital.appointment.dto.AppointmentPayloadDTO;
import com.hospital.appointment.dto.PatientDTO;
import com.hospital.appointment.enums.Dicease;
import com.hospital.appointment.enums.ExecutionEventPoint;
import com.hospital.appointment.exception.PatientNotExistsException;
import com.hospital.appointment.sagas.SagaOrchestrator;
import com.hospital.appointment.service.AppointmentService;
import com.hospital.appointment.service.PatientService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/appointments")
public class AppointmentController {
	
	private final PatientService myPatientService;
	private final SagaOrchestrator mySagaOrchestrator;
	
	 @PostMapping("/patient/{id}/dicease/{dicease}")
	 public AppointmentDTO save( @PathVariable Long id ,@PathVariable Dicease dicease)  {
		 //check if patient exists
		 PatientDTO myPatient=myPatientService.getById(id);
		 
		 //if true save new Appointment and update patient appointment list 
		 if(myPatient==null) {
			 throw new PatientNotExistsException("Patient with ID:"+ id +" does't exists.");
		 }else {
			 AppointmentPayloadDTO myPayload=new AppointmentPayloadDTO();
			 myPayload.setPatient(myPatient);
			 myPayload.setExecutionEventPoint(ExecutionEventPoint.CREATE_APPOINTMENT_RECEIVED);
			 
			return mySagaOrchestrator.handleAppointmentSaga(myPayload);
		 }
	 }
	
}
