package com.hospital.appointment.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.appointment.dto.AppointmentDTO;
import com.hospital.appointment.dto.PatientDTO;
import com.hospital.appointment.entities.Patient;
import com.hospital.appointment.enums.Disease;
import com.hospital.appointment.enums.ExecutionEventPoint;
import com.hospital.appointment.exception.PatientNotExistsException;
import com.hospital.appointment.sagas.SagaOrchestrator;
import com.hospital.appointment.service.AppointmentService;
import com.hospital.appointment.service.PatientService;
import com.hospital.payloads.AppointmentPayloadDTO;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@RequestMapping("/appointments")
public class AppointmentController {
	
	private final PatientService myPatientService;
	private final SagaOrchestrator mySagaOrchestrator;
	
	@PostMapping("/patient/{id}/dicease/{dicease}")
	public ResponseEntity<Integer> saveNewAppointment( @PathVariable Long id ,@PathVariable Disease disease)  {
		//check if patient exists
		PatientDTO myPatient=myPatientService.getById(id);
		 
		//if true save new Appointment and update patient appointment list 
		if(myPatient==null) {
			throw new PatientNotExistsException("Patient with ID:"+ id +" doesn't exist.");
		}else {
			AppointmentDTO myPayload=new AppointmentDTO();
			myPayload.setPatient(myPatient);			 
			mySagaOrchestrator.handleAppointmentSaga(myPayload,ExecutionEventPoint.CREATE_APPOINTMENT_RECEIVED);
			return new ResponseEntity<Integer>(HttpStatus.OK);
		}
	}
}
