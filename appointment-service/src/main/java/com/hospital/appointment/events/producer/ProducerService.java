package com.hospital.appointment.events.producer;

import com.hospital.appointment.dto.AppointmentDTO;
import com.hospital.appointment.dto.AppointmentPayloadDTO;

public interface ProducerService {

	void sendToSaveBills(AppointmentDTO myAppointment);

	void sendToCancelTreatmentPlan(AppointmentDTO myAppointment);

	void sendToCancelBill(AppointmentDTO myAppointment);

	void sendToRequestNewTreatmentPlan(AppointmentDTO myAppointment);

	void sendToRequestNewTreatmentPlan(AppointmentPayloadDTO myAppointment);
}
