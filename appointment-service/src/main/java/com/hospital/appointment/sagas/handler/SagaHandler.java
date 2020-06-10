package com.hospital.appointment.sagas.handler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.hospital.appointment.dto.AppointmentDTO;
import com.hospital.appointment.enums.AppointmentState;
import com.hospital.appointment.enums.ExecutionEventPoint;
import com.hospital.appointment.sagas.handler.SagaHandler.SagaStep;
import com.hospital.appointment.service.AppointmentService;
import com.hospital.payloads.AppointmentPayloadDTO;

import lombok.extern.slf4j.Slf4j;

public interface SagaHandler {

	AppointmentDTO manageNextSagaStep(AppointmentDTO myPayload,ExecutionEventPoint currentExecutionPoint);

	List<ExecutionEventPoint> getExecutionPointsOwnedFromHandler();

	void initilizeSagaDefinition();
	
	LinkedHashMap<ExecutionEventPoint,SagaStep> getSagaDefinition();
	
	class SagaStep {
		
		BiFunction<AppointmentService,AppointmentDTO,AppointmentDTO> onReply;
		BiFunction<AppointmentService,AppointmentDTO,AppointmentDTO> onFailure;
		AppointmentService myAppointmentService;
		
		protected SagaStep(BiFunction<AppointmentService,AppointmentDTO,AppointmentDTO> onReply, 
				BiFunction<AppointmentService,AppointmentDTO,AppointmentDTO> onFailure, 
				AppointmentService myAppointmentService) {
			this.onFailure=onFailure;
			this.onReply=onReply;
			this.myAppointmentService=myAppointmentService;
		}
		
		protected AppointmentDTO handleReply(AppointmentDTO myAppointment) {
			return this.onReply.apply(this.myAppointmentService, myAppointment);
		}
		
		protected AppointmentDTO handleFailure(AppointmentDTO myAppointment) {
			if(this.onFailure==null) return myAppointment;
			else return this.onFailure.apply(this.myAppointmentService, myAppointment);					
		}

	}
	
	default void triggerCompensationMeasures(ExecutionEventPoint currentExecutionEventPoint,
			AppointmentDTO myAppointment) {
		LinkedHashMap<ExecutionEventPoint,SagaStep> sagaDefinition=getSagaDefinition();
		
		List<ExecutionEventPoint> keyList = new ArrayList<ExecutionEventPoint>(sagaDefinition.keySet());
		keyList.forEach(point->System.out.println("====================== keyList:"+point));
		
		int idx = keyList.indexOf(currentExecutionEventPoint);
		for ( int i = idx ; i >= 0 ; i-- ) {
			SagaStep myStep=sagaDefinition.get(keyList.get(i));
			System.out.println("====================== executing compansations for mystep: "+keyList.get(i) +" index: "+i);
			myStep.handleFailure(myAppointment);
		}		
	}

}
