package com.hospital.treatment.services;

import java.util.Date;

import com.hospital.treatment.entities.Doctor;
import com.hospital.treatment.entities.TreatmentCatalog;
import com.hospital.treatment.entities.TreatmentPlan;

public interface TreatmentPlanService {

	TreatmentPlan saveNewTreatmentPlan(Doctor doctor, Date date, TreatmentCatalog treatmentCatalog,Long patentId);

}
