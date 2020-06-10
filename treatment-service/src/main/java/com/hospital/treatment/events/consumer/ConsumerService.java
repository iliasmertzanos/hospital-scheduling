package com.hospital.treatment.events.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.hospital.payloads.AppointmentPayloadDTO;
import com.hospital.treatment.services.MessageHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerService {
	
	private final MessageHandler myMessageHandler;
    
//    @RabbitListener(queues = "${queue.financial.result}")
//    public void handleQueueFinancialResultMessageReception(AppointmentPayloadDTO myPayload) {
//    	log.info(" ============================  Message received in Queue queue-check-fin-result : " + myPayload);
//    	myPayload.setExecutionEventPoint(ExecutionEventPoint.FINANCIAL_RESULT_RECEIVED);
//    	
//    	mySagaOrchestrator.handleAppointmentSaga(myPayload);
//    }
//    
//    @RabbitListener(queues = "${queue.treatment.result}")
//    public void handleQueueTreatmentResultMessageReception(AppointmentPayloadDTO myPayload) {
//    	log.info(" ============================  Message received in Queue queue-treatment-plan-result : " + myPayload);
//    	myPayload.setExecutionEventPoint(ExecutionEventPoint.TREATMENT_RESULT_RECEIVED);
//    	
//    	mySagaOrchestrator.handleAppointmentSaga(myPayload);
//    }
	
	@RabbitListener(queues = "${queue.treatment.request}")
    public void handleQueueTreatmentRequestMessageReception(AppointmentPayloadDTO myPayloadDTO) {
    	
    	log.info(" ============================  Message received in Treatment Service queue-treatment-plan-new\n: " + myPayloadDTO);
    	
    	log.info(" ============================  Creating new treatment plan ....");
    	
    	myMessageHandler.handleNewIncomingPlanRequest(myPayloadDTO);
    }
    
    @RabbitListener(queues = "${queue.treatment.cancel}")
    public void handleQueueTreatmentCancelMessageReception(AppointmentPayloadDTO myPayloadDTO) {
    	
    	log.info(" ============================  Message received in treatment-plan-cancel\n: " + myPayloadDTO);
    	
    	log.info(" ============================  Canceling treatment plan ....");
    	
    }
}
