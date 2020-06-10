package com.hospital.treatment.services;

import java.util.List;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;

import com.hospital.payloads.AppointmentPayloadDTO;
import com.hospital.treatment.dto.DoctorDTO;
import com.hospital.treatment.dto.DoctorFreeSlotsDTO;
import com.hospital.treatment.dto.TreatmentCatalogDTO;
import com.hospital.treatment.dto.TreatmentPlanDTO;
import com.hospital.treatment.entities.Doctor;
import com.hospital.treatment.entities.TreatmentCatalog;
import com.hospital.treatment.entities.TreatmentPlan;
import com.hospital.treatment.enums.Disease;
import com.hospital.treatment.events.producer.ProducerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageHandler {
	
	private final ProducerService myProducerService;
	
	private final TreatmentCatalogService myTreatmentCatalogService;
	
	private final DoctorService myDoctorService;
	
	private final TreatmentPlanService myTreatmentPlanService;
	
	public void handleNewIncomingPlanRequest(AppointmentPayloadDTO myPayloadDTO) {
		
		myPayloadDTO=processNewIncomingPlanRequest(myPayloadDTO);
		
		if(myPayloadDTO!=null)
			sendTreatmentPlanResponse(myPayloadDTO);
		
	}
	
	private void sendTreatmentPlanResponse(AppointmentPayloadDTO myPayloadDTO) {
		myProducerService.sendTreatmentPlanResult(myPayloadDTO);
	}

	private AppointmentPayloadDTO processNewIncomingPlanRequest(AppointmentPayloadDTO myPayloadDTO) {
		log.debug("===================================== New request for treatment plan received, searching for clinical area ...");
		
		String dicease=myPayloadDTO.getDisease();
		//check if disease exists 
		if(!isDiseaseValueValid(myPayloadDTO)) {
			log.debug(" ===================================== Not such disease found.");
			return myPayloadDTO; 		
		}
		
		
		
		//find treatment catalog record for this disease
		TreatmentCatalog myTreatmentCatalog=this.myTreatmentCatalogService.findByDisease(Enum.valueOf(Disease.class, dicease));
		
		if(myTreatmentCatalog==null) {
			log.debug(" ===================================== No clinical area covers this disease.");
			myPayloadDTO.setRejectionReason("No clinical area covers this disease.");
		}
		
		log.debug("===================================== Clinical area found: "+ myTreatmentCatalog.getClinicalArea());
		
		log.debug("===================================== Searching for the next available doctor to take over the treatment plan ...");
		
		//find all doctors that covers this disease with free slots
		List<DoctorFreeSlotsDTO> doctorsList=this.myDoctorService
				.findDoctorsWithFreeSlots(myTreatmentCatalog.getDuration()
						, myTreatmentCatalog.getClinicalArea());
		
		if(doctorsList==null || doctorsList.isEmpty()) {
			myPayloadDTO.setRejectionReason("No available Doctor found for the disease: "+dicease+".");
		}
		
		// find first doctor with free capacities 		
		DoctorFreeSlotsDTO myDoctorSlot=doctorsList.get(0);

		log.debug("===================================== Next available doctor found: "+ myDoctorSlot.getDoctorId());
		
		Doctor myDoctor=this.myDoctorService.findById(myDoctorSlot.getDoctorId());
		
		log.debug("===================================== Saving new treatment plan ... : "+ myDoctorSlot.toString());
		
		//if there is an available		
		// save new treatment plan and return
		TreatmentPlan treatmentPlan=this.myTreatmentPlanService.saveNewTreatmentPlan(myDoctor,
				myDoctorSlot.getSlotDate(), 
				myTreatmentCatalog,
				myPayloadDTO.getPatientId());		
		
		log.debug("===================================== New treatment plan sacessfully saved ... : "+ treatmentPlan.toString());
				
		return this.mapToPayload(myPayloadDTO, treatmentPlan);
		
	}

	private boolean isDiseaseValueValid(AppointmentPayloadDTO myPayloadDTO) {
		if( !EnumUtils.isValidEnum(Disease.class, myPayloadDTO.getDisease())) {
			myPayloadDTO.setRejectionReason("Not such disease found.");
			return false;
		}
		return true;
	}
	
	public TreatmentPlanDTO handleIncomingCancelRequest(AppointmentPayloadDTO myPayloadDTO) {
		
		
		
		return null;
		
	}
	
	AppointmentPayloadDTO mapToPayload(AppointmentPayloadDTO myPayload, TreatmentPlan treatmentPlan ) {
		myPayload.setClinic(treatmentPlan.getTreatmentCatalog().getClinicalArea().toString());
		myPayload.setDate(treatmentPlan.getDate());
		myPayload.setDoctorId(treatmentPlan.getDoctor().getId());
		myPayload.setDoctorName(treatmentPlan.getDoctor().getFirstName()+" "+treatmentPlan.getDoctor().getLastName());
		myPayload.setOnPrivateExpences(treatmentPlan.getTreatmentCatalog().getOnPrivateExpences());
		myPayload.setTreatmentCosts(treatmentPlan.getTreatmentCatalog().getTreatmentCost());
		return myPayload;
	}

}
