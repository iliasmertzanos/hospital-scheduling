package com.hospital.appointment.events.producer;

import com.hospital.appointment.dto.AppointmentDTO;
import com.hospital.payloads.AppointmentPayloadDTO;

public interface ProducerService {

	void sendToSaveBills(AppointmentPayloadDTO myAppointment);

	void sendToCancelTreatmentPlan(AppointmentPayloadDTO myAppointment);

	void sendToCancelBill(AppointmentPayloadDTO myAppointment);

	void sendToRequestNewTreatmentPlan(AppointmentPayloadDTO myAppointment);
}
