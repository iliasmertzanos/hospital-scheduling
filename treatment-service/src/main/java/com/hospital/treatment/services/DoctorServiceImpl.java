package com.hospital.treatment.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import org.apache.commons.lang3.tuple.Pair;
import org.hibernate.mapping.Collection;
import org.javatuples.Triplet;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.hospital.treatment.dto.DoctorDTO;
import com.hospital.treatment.dto.DoctorFreeSlotsDTO;
import com.hospital.treatment.dto.TreatmentCatalogDTO;
import com.hospital.treatment.entities.Doctor;
import com.hospital.treatment.entities.TreatmentCatalog;
import com.hospital.treatment.enums.ClinicalArea;
import com.hospital.treatment.repositories.DoctorRepository;
import com.hospital.treatment.repositories.TreatmentCatalogRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
	
	private final DoctorRepository myDoctorRepository;
	ModelMapper modelMapper=new ModelMapper();
	
	@Override
	public List<DoctorDTO> findByClinicalArea(ClinicalArea clinicalArea){
		List<Doctor> doctorList=myDoctorRepository.findByClinicalArea(clinicalArea);
		
		if(doctorList==null || doctorList.isEmpty()) {
			return null;
		}
		
		return doctorList.stream()
		.map(doctor->modelMapper.map(doctor,DoctorDTO.class))
		.collect(Collectors.toList());
		
	}

	@Override
	public List<DoctorFreeSlotsDTO> findDoctorsWithFreeSlots(Double treatmentDuration,ClinicalArea clinicalArea) {
		Pageable myPageable=PageRequest.of(0, 1);
		List<DoctorFreeSlotsDTO> doctorList =myDoctorRepository.findDoctorsWithFreeSlots(clinicalArea,treatmentDuration,myPageable);
		
		if(doctorList==null || doctorList.isEmpty()) {
			return null;
		}
		
		return doctorList;
	}

	@Override
	public Doctor findById(Long doctorId) {
		return this.myDoctorRepository.findById(doctorId).orElse(null);
	}

}
