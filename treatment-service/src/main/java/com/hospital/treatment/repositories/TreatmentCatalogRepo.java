package com.hospital.treatment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.treatment.entities.TreatmentCatalog;

public interface TreatmentCatalogRepo extends JpaRepository<TreatmentCatalog, Long> {

}
