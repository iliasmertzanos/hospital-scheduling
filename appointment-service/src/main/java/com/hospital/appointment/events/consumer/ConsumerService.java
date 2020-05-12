package com.hospital.appointment.events.consumer;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.hospital.appointment.dto.AppointmentDTO;
import com.hospital.appointment.enums.ExecutionEventPoint;
import com.hospital.appointment.sagas.SagaOrchestrator;
import com.hospital.payloads.AppointmentPayloadDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerService {

	private final SagaOrchestrator mySagaOrchestrator;
	
	ModelMapper modelMapper=new ModelMapper();
    
    @RabbitListener(queues = "${queue.financial.result}")
    public void handleQueueFinancialResultMessageReception(AppointmentPayloadDTO myPayload) {
    	log.info(" ============================  Message received in Appointment Service Queue queue-check-fin-result : " + myPayload);
//    	myPayload.setExecutionEventPoint(ExecutionEventPoint.FINANCIAL_RESULT_RECEIVED);
    	
    	mySagaOrchestrator.handleAppointmentSaga(modelMapper.map(myPayload,AppointmentDTO.class),ExecutionEventPoint.FINANCIAL_RESULT_RECEIVED);
    }
    
    @RabbitListener(queues = "${queue.treatment.result}")
    public void handleQueueTreatmentResultMessageReception(AppointmentPayloadDTO myPayload) {
    	log.info(" ============================  Message received in Appointment Service Queue queue-treatment-plan-result : " + myPayload);
//    	myPayload.setExecutionEventPoint();
    	
    	mySagaOrchestrator.handleAppointmentSaga(modelMapper.map(myPayload,AppointmentDTO.class),ExecutionEventPoint.TREATMENT_RESULT_RECEIVED);
    }
}
