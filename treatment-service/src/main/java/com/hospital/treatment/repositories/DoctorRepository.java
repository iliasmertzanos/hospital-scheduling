package com.hospital.treatment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.treatment.entities.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
