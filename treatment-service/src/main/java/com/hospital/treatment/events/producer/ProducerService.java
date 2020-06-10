package com.hospital.treatment.events.producer;

import com.hospital.payloads.AppointmentPayloadDTO;

public interface ProducerService {
	
	void sendTreatmentPlanResult(AppointmentPayloadDTO myAppointment);
}
