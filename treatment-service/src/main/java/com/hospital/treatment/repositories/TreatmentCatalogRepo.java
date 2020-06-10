package com.hospital.treatment.repositories;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.treatment.entities.TreatmentCatalog;
import com.hospital.treatment.enums.ClinicalArea;
import com.hospital.treatment.enums.Disease;

public interface TreatmentCatalogRepo extends JpaRepository<TreatmentCatalog, Long> {
	public Optional<TreatmentCatalog> findByDiceaseCode(Disease diceaseCode);
}
