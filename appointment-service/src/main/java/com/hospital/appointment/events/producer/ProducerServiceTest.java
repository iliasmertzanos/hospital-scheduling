package com.hospital.appointment.events.producer;

import com.hospital.appointment.dto.AppointmentDTO;
import com.hospital.appointment.dto.AppointmentPayloadDTO;

public interface ProducerServiceTest {

	void sendCheckAnswerToAppointment(AppointmentPayloadDTO myAppointment);

	void sendTreatmentPlanResult(AppointmentPayloadDTO myAppointment);
}
