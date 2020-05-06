package com.hospital.appointment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.appointment.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{
	
}
