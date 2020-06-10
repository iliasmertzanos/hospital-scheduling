package com.hospital.treatment.services;

import com.hospital.treatment.dto.TreatmentCatalogDTO;
import com.hospital.treatment.entities.TreatmentCatalog;
import com.hospital.treatment.enums.Disease;

public interface TreatmentCatalogService {
	public TreatmentCatalog findByDisease(Disease disease);
}
