package com.hospital.appointment.sagas.handler;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.hospital.appointment.dto.AppointmentDTO;
import com.hospital.appointment.dto.AppointmentPayloadDTO;
import com.hospital.appointment.enums.AppointmentState;
import com.hospital.appointment.enums.ExecutionEventPoint;
import com.hospital.appointment.service.AppointmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateAppointmentSagaHandler implements SagaHandler {
	
	private final AppointmentService myAppointmentService;
	
	private final  ModelMapper modelMapper=new ModelMapper();
	
	private LinkedHashMap<ExecutionEventPoint,SagaStep> sagaDefinition;

	private Object object;
	
	@Override
	public AppointmentDTO manageNextSagaStep(AppointmentPayloadDTO myPayload) {
		ExecutionEventPoint currentExecutionPoint= myPayload.getExecutionEventPoint();
		log.info("  ============================ RECEIVED AppointmentPayloadDTO in CreateAppointmentSagaHandler: "+myPayload+ " with status: "+myPayload.getState());
		
		AppointmentDTO myAppointment=modelMapper.map(myPayload, AppointmentDTO.class);
		log.info("  ============================ RECEIVED AppointmentPayloadDTO in CreateAppointmentSagaHandler MAPPED: "+myAppointment);
		
		try{
			return sagaDefinition.get(currentExecutionPoint).handleReply(myAppointment);
		}catch (ObjectOptimisticLockingFailureException e) {
			log.warn("  ============================ Aboarding Create Appointment Saga due to Optimistic Exception ");
			log.warn("  ============================ Triggering compansating measurements");
			triggerCompensationMeasures(currentExecutionPoint, myAppointment);
		}		
		 return myAppointment;
	}	
	
	@Override
	public void initilizeSagaDefinition() {
		sagaDefinition= new LinkedHashMap<ExecutionEventPoint, SagaStep>() {{
	        put(ExecutionEventPoint.CREATE_APPOINTMENT_RECEIVED, new SagaStep(AppointmentService::handleNewIncomingAppointment, null,myAppointmentService));
	        put(ExecutionEventPoint.TREATMENT_RESULT_RECEIVED, new SagaStep(AppointmentService::handleTreatmentResultReception, AppointmentService::handleTreatmentResultFailure,myAppointmentService));
	        put(ExecutionEventPoint.FINANCIAL_RESULT_RECEIVED, new SagaStep(AppointmentService::handleFinancialResultReception, AppointmentService::handleFinancialResultFailure,myAppointmentService));
	        
	    }};		
	}

	@Override
	public LinkedHashMap<ExecutionEventPoint, SagaStep> getSagaDefinition() {
		return this.sagaDefinition;
	}

	@Override
	public List<ExecutionEventPoint> getExecutionPointsOwnedFromHandler() {
		return Arrays.asList(ExecutionEventPoint.CREATE_APPOINTMENT_RECEIVED,
				ExecutionEventPoint.TREATMENT_RESULT_RECEIVED,
				ExecutionEventPoint.FINANCIAL_RESULT_RECEIVED);
	}

}
