package com.hospital.treatment.repositories;

import java.util.Date;
import java.util.List;

import org.javatuples.Triplet;
import org.javatuples.Tuple;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hospital.treatment.dto.DoctorFreeSlotsDTO;
import com.hospital.treatment.entities.Doctor;
import com.hospital.treatment.enums.ClinicalArea;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	public List<Doctor> findByClinicalArea(ClinicalArea clinicalArea);
	
	@Query("select new com.hospital.treatment.dto.DoctorFreeSlotsDTO( (doc.workingHours-sum(tc.duration)),tp.date,doc.id) from TreatmentPlan tp" + 
			" left join tp.doctor doc " + 
			" left join tp.treatmentCatalog tc"+
			" where doc.clinicalArea=:clinicalArea"+
			" group by tp.date, doc.id "+
			" HAVING col_0_0_>=:treatmentDuration "+
			" order by col_0_0_ asc " )
	public List<DoctorFreeSlotsDTO > findDoctorsWithFreeSlots(@Param("clinicalArea") ClinicalArea clinicalArea,
			@Param("treatmentDuration") Double treatmentDuration,
			Pageable pageable);
	
}
