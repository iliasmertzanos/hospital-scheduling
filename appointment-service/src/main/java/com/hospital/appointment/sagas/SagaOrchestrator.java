package com.hospital.appointment.sagas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.appointment.dto.AppointmentDTO;
import com.hospital.appointment.dto.AppointmentPayloadDTO;
import com.hospital.appointment.enums.AppointmentState;
import com.hospital.appointment.enums.ExecutionEventPoint;
import com.hospital.appointment.sagas.handler.SagaHandler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SagaOrchestrator {
	
	private final Map<ExecutionEventPoint, SagaHandler> sagaHandlerRegistry = new HashMap<ExecutionEventPoint, SagaHandler>();

	@Autowired
	public SagaOrchestrator(List<SagaHandler> sagaHandlerList){
		for (SagaHandler service: sagaHandlerList){
			service.initilizeSagaDefinition();
			for(ExecutionEventPoint point:service.getExecutionPointsOwnedFromHandler()) {
				register(point, service);
			}
		}
	}

	private void register(ExecutionEventPoint serviceName, SagaHandler service) {
		this.sagaHandlerRegistry.put(serviceName, service);
	}

	private SagaHandler getSagaHandler(ExecutionEventPoint eventPoint){
		return this.sagaHandlerRegistry.get(eventPoint);
	}
	
	public AppointmentDTO handleAppointmentSaga(AppointmentDTO myPayload,ExecutionEventPoint currentExecutionPoint) {
		SagaHandler mySagaHandler=this.getSagaHandler(currentExecutionPoint);
		return mySagaHandler.manageNextSagaStep(myPayload,currentExecutionPoint);
	}
}