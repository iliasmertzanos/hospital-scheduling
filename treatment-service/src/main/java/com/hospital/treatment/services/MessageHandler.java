package com.hospital.treatment.services;

import com.hospital.treatment.dto.AppointmentPayloadDTO;
import com.hospital.treatment.dto.TreatmentPlanDTO;

public class MessageHandler {
	
	public TreatmentPlanDTO handleNewIncomingPlanRequest(AppointmentPayloadDTO myPayloadDTO) {
		
		//check if disease exists 
		
		//find all doctors that covers this disease
		
		// find first doctor with free capacities 
		
		//if there is an available		
		// save new treatment plan and return
		
		//otherwise return rejection reason
		
		return null;
		
	}
	
	public TreatmentPlanDTO handleIncomingCancelRequest(AppointmentPayloadDTO myPayloadDTO) {
		
		
		
		return null;
		
	}

}
