package com.hospital.appointment;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hospital.appointment.dto.AppointmentDTO;
import com.hospital.appointment.dto.PatientDTO;
import com.hospital.appointment.entities.Appointment;
import com.hospital.appointment.enums.AppointmentState;
import com.hospital.appointment.enums.Disease;
import com.hospital.appointment.enums.ExecutionEventPoint;
import com.hospital.appointment.events.consumer.ConsumerServiceTest;
import com.hospital.appointment.events.producer.ProducerService;
import com.hospital.appointment.events.producer.ProducerServiceTest;
import com.hospital.appointment.repositories.AppointmentRepository;
import com.hospital.appointment.sagas.SagaOrchestrator;
import com.hospital.appointment.service.AppointmentService;
import com.hospital.appointment.service.PatientService;
import com.hospital.payloads.AppointmentPayloadDTO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = AppointmentApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class AppointmentAsychronousComunicationTester {
	
	@Autowired
	AppointmentService myAppointmentService;
	
	@Autowired
	PatientService myPatientService;
	
	@Autowired
	AppointmentRepository myAppointmentRepo;
	
	@Autowired 
	ProducerServiceTest myRabbitMQProducerTest;
	
	@Autowired 
	ProducerService myRabbitMQProducer;
	
	@Autowired
	ConsumerServiceTest myConsumerServiceTest;
	
	@Autowired
	SagaOrchestrator mySagaOrchestrator;
	
	private  ModelMapper modelMapper=new ModelMapper() ;
	
	@Test
	public void test_new_Appointment_received_and_new_Treatment_Plan_Request() throws InterruptedException {
		PatientDTO myPatient=new PatientDTO();
		myPatient.setId(2L);
		myPatient.setDisease(Disease.PARADONTITIS);
		
		AppointmentDTO myNewAppointment=new AppointmentDTO();
		myNewAppointment.setPatient(myPatient);
		
		myNewAppointment=mySagaOrchestrator.handleAppointmentSaga(myNewAppointment,ExecutionEventPoint.CREATE_APPOINTMENT_RECEIVED);
		assertNotNull(myNewAppointment);
		
//		Thread.sleep(1000);	
//		
//		log.info("=============================  Waiting in test consumer to receive the treatment plan request ... ");
//		
//		assertTrue(myConsumerServiceTest.treatmentPlanRequestMessageReception);
//		
//		log.info("=============================  Message received !!!!!!!!!!");
//		
//		AppointmentPayloadDTO myPayload=modelMapper.map(myNewAppointment,AppointmentPayloadDTO.class);
//		
//		log.info("=============================  Setting Treatment plan result");
//		myPayload.setClinic("Pathological");
//		myPayload.setDoctorId(443252L);
//		myPayload.setDoctorName("Humar Tamimi");
//		myPayload.setRoom("23");
//		myPayload.setTreatmentCosts(123445.56);
//		
//		log.info("=============================  Sending a MOCK Answer for a Treatment plan result to simulate answer : ");
//		myRabbitMQProducerTest.sendTreatmentPlanResult(myPayload);
    	
    	Thread.sleep(3000);
    	
    	Appointment myNewAppointmentEntity=myAppointmentRepo.findById(myNewAppointment.getId()).get();
		
		assertTrue(myNewAppointmentEntity.getState()==AppointmentState.REJECTED);
		assertTrue("Humar Tamimi".equals(myNewAppointmentEntity.getDoctorName()));
		
	}

}
