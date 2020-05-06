package com.hospital.appointment.service;

import java.util.List;

import com.hospital.appointment.dto.AppointmentDTO;
import com.hospital.appointment.dto.PatientDTO;
import com.hospital.appointment.entities.Appointment;
import com.hospital.appointment.enums.Dicease;

public interface PatientService {

	PatientDTO getById(Long patientId);

	List<Appointment> getAppointmentList(Long patientId);

}
