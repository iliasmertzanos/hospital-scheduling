package com.hospital.treatment.services;

import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;

import org.apache.commons.lang3.tuple.Pair;
import org.javatuples.Triplet;

import com.hospital.treatment.dto.DoctorDTO;
import com.hospital.treatment.dto.DoctorFreeSlotsDTO;
import com.hospital.treatment.entities.Doctor;
import com.hospital.treatment.enums.ClinicalArea;

public interface DoctorService {

	List<DoctorDTO> findByClinicalArea(ClinicalArea clinicalArea);

	public List<DoctorFreeSlotsDTO> findDoctorsWithFreeSlots(Double treatmentDuration, ClinicalArea clinicalArea);

	Doctor findById(Long doctorId);
	
}
