package com.hospital.appointment.sagas.handler;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hospital.appointment.dto.AppointmentDTO;
import com.hospital.appointment.dto.AppointmentPayloadDTO;
import com.hospital.appointment.enums.AppointmentState;
import com.hospital.appointment.enums.ExecutionEventPoint;

@Service
public class CancelAppointmentSagaHandler implements SagaHandler {

	@Override
	public AppointmentDTO manageNextSagaStep(AppointmentPayloadDTO myPayload) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initilizeSagaDefinition() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LinkedHashMap<ExecutionEventPoint, SagaStep> getSagaDefinition() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<ExecutionEventPoint> getExecutionPointsOwnedFromHandler() {
		return Arrays.asList(ExecutionEventPoint.CANCEL_APPOINTMENT_RECEIVED);
	}

}
