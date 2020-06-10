package com.hospital.treatment.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.hospital.treatment.dto.TreatmentCatalogDTO;
import com.hospital.treatment.entities.TreatmentCatalog;
import com.hospital.treatment.enums.Disease;
import com.hospital.treatment.events.producer.ProducerService;
import com.hospital.treatment.repositories.TreatmentCatalogRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TreatmentCatalogServiceImpl implements TreatmentCatalogService{
	
	private final TreatmentCatalogRepo myTreatmentCatalogRepo;
	
	ModelMapper modelMapper=new ModelMapper();

	@Override
	public TreatmentCatalog findByDisease(Disease disease) {
		TreatmentCatalog myTreatmentCatalog=myTreatmentCatalogRepo.findByDiceaseCode(disease).orElse(null);
		
		if(myTreatmentCatalog==null) {
			return null;
		}
		
		return myTreatmentCatalog;
	}

}
