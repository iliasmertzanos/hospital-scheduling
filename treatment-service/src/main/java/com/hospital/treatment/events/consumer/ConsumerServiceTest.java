package com.hospital.treatment.events.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.hospital.payloads.AppointmentPayloadDTO;
import com.hospital.treatment.events.producer.ProducerService;
import com.hospital.treatment.events.producer.ProducerServiceTest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerServiceTest {
	
	public boolean financialSaveMessageReception=false;
	
	public boolean financialCancelMessageReception=false;
	
	public boolean treatmentPlanRequestMessageReception=false;
	
	public boolean treatmentCancelMessageReception=false;
    
//    //TEST ONLY
//    @RabbitListener(queues = "${queue.financial.save}")
//    public void handleQueueFinancialSaveMessageReception(AppointmentDTO myAppointment) {
//    	log.info(" ============================  Message received in Queue queue-new-bill \n: " + myAppointment);
//    	
//    	log.info(" ============================  Checking if patient is financial approved after getting request for checking....");
//    	
//    	financialSaveMessageReception=true;
//    	
//    	log.info(" ============================  Financial check is ready!!!!!....");
//    	
//    }
    
//  //TEST ONLY
//    @RabbitListener(queues = "${queue.financial.cancel}")
//    public void handleQueueFinancialCancelMessageReception(AppointmentDTO myAppointment) {
//    	log.info(" ============================  Message received in Queue queue-cancel-bill\n: " + myAppointment);
//    	
//    	log.info(" ============================  Canceling patient bill ....");
//    	
//    	financialCancelMessageReception=true;
//    	
//    }
    
  //TEST ONLY
    
    
//
//    @RabbitListener(queues = "${queue.C}")
//    public void handleQueueCMessageReception(Payload payload) {
//        LOGGER.info("Message received in Queue C : " + payload.getMessage());
//    }
//
//    @RabbitListener(queues = "${queue.D}")
//    public void handleQueueDMessageReception(Payload payload) {
//        LOGGER.info("Message received in Queue D : " + payload.getMessage());
//    }
//
//    @RabbitListener(queues = "${queue.E}")
//    public void handleQueueEMessageReception(Payload payload) {
//        LOGGER.info("Message received in Queue E : " + payload.getMessage());
//    }
//
//    @RabbitListener(queues = "${queue.F}")
//    public void handleQueueFMessageReception(Payload payload) {
//        LOGGER.info("Message received in Queue F : " + payload.getMessage());
//    }
}
