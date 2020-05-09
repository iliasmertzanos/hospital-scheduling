package com.hospital.appointment.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.appointment.dto.AppointmentDTO;
import com.hospital.appointment.dto.AppointmentPayloadDTO;
import com.hospital.appointment.dto.PatientDTO;
import com.hospital.appointment.entities.Patient;
import com.hospital.appointment.enums.Disease;
import com.hospital.appointment.enums.ExecutionEventPoint;
import com.hospital.appointment.exception.PatientNotExistsException;
import com.hospital.appointment.sagas.SagaOrchestrator;
import com.hospital.appointment.service.AppointmentService;
import com.hospital.appointment.service.PatientService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@RequestMapping("/patients")
public class PatientController {
	
	private final PatientService myPatientService;
	
	private ModelMapper modelMapper=new ModelMapper();
	 
	 @PostMapping
	 public PatientDTO saveNewPatient( @RequestBody PatientDTO patient)  {
		 return this.myPatientService.savePatient(modelMapper.map(patient,Patient.class));
	 }
}
