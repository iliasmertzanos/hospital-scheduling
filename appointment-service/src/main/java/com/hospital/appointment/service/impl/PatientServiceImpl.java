package com.hospital.appointment.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.hospital.appointment.dto.AppointmentDTO;
import com.hospital.appointment.dto.PatientDTO;
import com.hospital.appointment.entities.Appointment;
import com.hospital.appointment.entities.Patient;
import com.hospital.appointment.enums.Dicease;
import com.hospital.appointment.repositories.AppointmentRepository;
import com.hospital.appointment.repositories.PatientRepository;
import com.hospital.appointment.service.PatientService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PatientServiceImpl implements PatientService {
	
	private final PatientRepository myPatientRepo;
	
	private  ModelMapper modelMapper=new ModelMapper() ;

	@Override
	public PatientDTO getById(Long patientId) {
		Patient myPatient=myPatientRepo
				.findById(patientId)
				.orElse(null);
		
		if(myPatient==null) return null;
		return modelMapper.map(myPatient, PatientDTO.class);
		
	}
	
	@Override
	public List<Appointment> getAppointmentList(Long patientId){
		Patient myPatient=myPatientRepo
				.findById(patientId)
				.orElse(null);
		
		if(myPatient==null) return null;
		return myPatient.getAppointment();
		
	}

	@Override
	public PatientDTO savePatient(Patient patient) {
		return modelMapper.map(this.myPatientRepo.saveAndFlush(patient),PatientDTO.class);
	}

}
