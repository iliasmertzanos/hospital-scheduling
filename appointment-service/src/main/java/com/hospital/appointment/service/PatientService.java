package com.hospital.appointment.service;

import java.util.List;

import com.hospital.appointment.dto.AppointmentDTO;
import com.hospital.appointment.dto.PatientDTO;
import com.hospital.appointment.enums.Dicease;
import com.hospital.appointment.models.Appointment;

public interface PatientService {

	PatientDTO getById(Long patientId);

	List<Appointment> getAppointmentList(Long patientId);

}
