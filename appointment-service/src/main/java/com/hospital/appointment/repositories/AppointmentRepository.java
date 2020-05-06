package com.hospital.appointment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.appointment.models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	
}
