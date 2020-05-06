package com.hospital.appointment.events.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.hospital.appointment.dto.AppointmentPayloadDTO;
import com.hospital.appointment.enums.AppointmentState;
import com.hospital.appointment.enums.ExecutionEventPoint;
import com.hospital.appointment.sagas.SagaOrchestrator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerService {

	private final SagaOrchestrator mySagaOrchestrator;
    
    @RabbitListener(queues = "${queue.financial.result}")
    public void handleQueueFinancialResultMessageReception(AppointmentPayloadDTO myPayload) {
    	log.info(" ============================  Message received in Queue queue-check-fin-result : " + myPayload);
    	myPayload.setExecutionEventPoint(ExecutionEventPoint.FINANCIAL_RESULT_RECEIVED);
    	
    	mySagaOrchestrator.handleAppointmentSaga(myPayload);
    }
    
    @RabbitListener(queues = "${queue.treatment.result}")
    public void handleQueueTreatmentResultMessageReception(AppointmentPayloadDTO myPayload) {
    	log.info(" ============================  Message received in Queue queue-treatment-plan-result : " + myPayload);
    	myPayload.setExecutionEventPoint(ExecutionEventPoint.TREATMENT_RESULT_RECEIVED);
    	
    	mySagaOrchestrator.handleAppointmentSaga(myPayload);
    }
}
