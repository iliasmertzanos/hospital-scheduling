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
public class AppointmentServiceUnitTester {
	
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
	public void test_optimistic_locking_on_treatment_plan_receptionr() throws InterruptedException {	
    	PatientDTO myPatient=new PatientDTO();
		myPatient.setId(2L);
		myPatient.setDisease(Disease.ASTHMA);
		
		AppointmentDTO myNewAppointment=new AppointmentDTO();
		myNewAppointment.setPatient(myPatient);
		
		myNewAppointment=mySagaOrchestrator.handleAppointmentSaga(myNewAppointment,ExecutionEventPoint.CREATE_APPOINTMENT_RECEIVED);
		assertNotNull(myNewAppointment);
		
		Thread.sleep(1000);
		
		log.info("=============================  Waiting in test consumer to receive the financial check request ... ");
		
		assertTrue(myConsumerServiceTest.treatmentPlanRequestMessageReception);
		
		log.info("=============================  Message received !!!!!!!!!!");
		
		myNewAppointment.setClinic("Pathological");
		myNewAppointment.setDoctorId(443252L);
		myNewAppointment.setDoctorName("Humar Tamimi");
		myNewAppointment.setRoom("23");
		myNewAppointment.setTreatmentCosts(123445.56);
		AppointmentPayloadDTO myPayload=modelMapper.map(myNewAppointment,AppointmentPayloadDTO.class);
		
		//Meanwhile a new change came and set appointment to canceled
		Appointment myNewAppointmentEntity=myAppointmentRepo.findById(myNewAppointment.getId()).get();
		
		log.info("=============================  Setting Status CANCELED ============= ");
		myNewAppointmentEntity.setState(AppointmentState.CANCELED);
		myAppointmentRepo.saveAndFlush(myNewAppointmentEntity);
		
		//Saga keeps on functioning as nothing happened
		//here we just simulate the approval or rejection, returning the state in the patient as it is:
		//but as it will be consumed by the createorder saga it should try update appointment to finacially_approved
		//this should throw an optimistic exception
		//which in turn will trigger the compensation measures to cancel the bill
    	log.info("=============================  Sending a MOCK Answer for a Treatment plan result to simulate answer : ");
		myRabbitMQProducerTest.sendTreatmentPlanResult(myPayload);
    	
    	Thread.sleep(1000);
    	
    	//check if the compensation measures to cancel the bill was triggered
    	assertTrue(myConsumerServiceTest.treatmentCancelMessageReception);   
		
	}
	
	@Test
	public void test_new_Appointment_received_and_new_Treatment_Plan_Request() throws InterruptedException {
		PatientDTO myPatient=new PatientDTO();
		myPatient.setId(2L);
		myPatient.setDisease(Disease.FOOBOO);
		
		AppointmentDTO myNewAppointment=new AppointmentDTO();
		myNewAppointment.setPatient(myPatient);
		
		myNewAppointment=mySagaOrchestrator.handleAppointmentSaga(myNewAppointment,ExecutionEventPoint.CREATE_APPOINTMENT_RECEIVED);
		assertNotNull(myNewAppointment);
		
		Thread.sleep(1000);	
		
		log.info("=============================  Waiting in test consumer to receive the treatment plan request ... ");
		
		assertTrue(myConsumerServiceTest.treatmentPlanRequestMessageReception);
		
		log.info("=============================  Message received !!!!!!!!!!");
		
		AppointmentPayloadDTO myPayload=modelMapper.map(myNewAppointment,AppointmentPayloadDTO.class);
		
		log.info("=============================  Setting Treatment plan result");
		myPayload.setClinic("Pathological");
		myPayload.setDoctorId(443252L);
		myPayload.setDoctorName("Humar Tamimi");
		myPayload.setTreatmentCosts(123445.56);
		
		log.info("=============================  Sending a MOCK Answer for a Treatment plan result to simulate answer : ");
		myRabbitMQProducerTest.sendTreatmentPlanResult(myPayload);
    	
    	Thread.sleep(1000);
    	
    	Appointment myNewAppointmentEntity=myAppointmentRepo.findById(myNewAppointment.getId()).get();
		
		assertTrue(myNewAppointmentEntity.getState()==AppointmentState.TREATMENT_APPROVED);
		assertTrue("Humar Tamimi".equals(myNewAppointmentEntity.getDoctorName()));
		
	}
	
	@Test
	public void test_newAppointmentreceived_and_was_rejected() throws InterruptedException {
		PatientDTO myPatient=new PatientDTO();
		myPatient.setId(2L);
		myPatient.setDisease(Disease.ASTHMA);
		
		AppointmentDTO myNewAppointment=new AppointmentDTO();
		myNewAppointment.setPatient(myPatient);
		
		myNewAppointment=mySagaOrchestrator.handleAppointmentSaga(myNewAppointment,ExecutionEventPoint.CREATE_APPOINTMENT_RECEIVED);
		assertNotNull(myNewAppointment);
		
		Thread.sleep(1000);	
		
		log.info("=============================  Waiting in test consumer to receive the treatment plan request ... ");
		
		assertTrue(myConsumerServiceTest.treatmentPlanRequestMessageReception);
		
		log.info("=============================  Message received !!!!!!!!!!");
		
		AppointmentPayloadDTO myPayload=modelMapper.map(myNewAppointment,AppointmentPayloadDTO.class);
		
		log.info("=============================  Setting Treatment plan result to rejected");
		myPayload.setRejectionReason("No available doctor");
		
		log.info("=============================  Sending a MOCK Answer for a Treatment plan result to simulate answer : ");
		myRabbitMQProducerTest.sendTreatmentPlanResult(myPayload);
    	
    	Thread.sleep(1000);
    	
    	Appointment myNewAppointmentEntity=myAppointmentRepo.findById(myNewAppointment.getId()).get();
		
		assertTrue(myNewAppointmentEntity.getState()==AppointmentState.REJECTED);
		
	}
	
	@Test
	public void test_new_Appointment_received_and_was_approved() throws InterruptedException {
		PatientDTO myPatient=new PatientDTO();
		myPatient.setId(2L);
		myPatient.setDisease(Disease.ASTHMA);
		
		AppointmentDTO myNewAppointment=new AppointmentDTO();
		myNewAppointment.setPatient(myPatient);
		
		myNewAppointment=mySagaOrchestrator.handleAppointmentSaga(myNewAppointment,ExecutionEventPoint.CREATE_APPOINTMENT_RECEIVED);
		assertNotNull(myNewAppointment);
		
		Thread.sleep(1000);	
		
		log.info("=============================  Waiting in test consumer to receive the treatment plan request ... ");
		
		assertTrue(myConsumerServiceTest.treatmentPlanRequestMessageReception);
		
		log.info("=============================  Message received !!!!!!!!!!");
		
		AppointmentPayloadDTO myPayload=modelMapper.map(myNewAppointment,AppointmentPayloadDTO.class);
		
		log.info("=============================  Setting Treatment plan result");
		myPayload.setClinic("Pathological");
		myPayload.setDoctorId(443252L);
		myPayload.setDoctorName("Humar Tamimi");
		myPayload.setTreatmentCosts(123445.56);
		
		log.info("=============================  Sending a MOCK Answer for a Treatment plan result to simulate answer : ");
		myRabbitMQProducerTest.sendTreatmentPlanResult(myPayload);
    	
    	Thread.sleep(2000);
    	
    	myNewAppointment=this.myAppointmentService.getAppointment(myPayload.getId());
		
		assertTrue(myNewAppointment.getState()==AppointmentState.TREATMENT_APPROVED);
		
		assertTrue(myConsumerServiceTest.financialSaveMessageReception);
		
		
		log.info("=============================  Sending a MOCK Answer for a financial result and check to simulate answer : ");
		myPayload=modelMapper.map(myNewAppointment,AppointmentPayloadDTO.class);
		myRabbitMQProducerTest.sendCheckAnswerToAppointment(myPayload);
		
		Thread.sleep(1000);
		
		myNewAppointment=this.myAppointmentService.getAppointment(myPayload.getId());
		
		assertTrue(myNewAppointment.getState()==AppointmentState.APPROVED);
		
	}
	
	@Test
	public void testin_optimistic_locking_on_financial_check_reception() throws InterruptedException {
		PatientDTO myPatient=new PatientDTO();
		myPatient.setId(2L);
		myPatient.setDisease(Disease.ASTHMA);
		
		AppointmentDTO myNewAppointment=new AppointmentDTO();
		myNewAppointment.setPatient(myPatient);
		myNewAppointment=mySagaOrchestrator.handleAppointmentSaga(myNewAppointment,ExecutionEventPoint.CREATE_APPOINTMENT_RECEIVED);
		assertNotNull(myNewAppointment);
		
		Thread.sleep(1000);	
		
		log.info("=============================  Waiting in test consumer to receive the treatment plan request ... ");
		
		assertTrue(myConsumerServiceTest.treatmentPlanRequestMessageReception);
		
		log.info("=============================  Message received !!!!!!!!!!");
		
		AppointmentPayloadDTO myPayload=modelMapper.map(myNewAppointment,AppointmentPayloadDTO.class);
		
		log.info("=============================  Setting Treatment plan result");
		myPayload.setClinic("Pathological");
		myPayload.setDoctorId(443252L);
		myPayload.setDoctorName("Humar Tamimi");
		myPayload.setTreatmentCosts(123445.56);
		
		log.info("=============================  Sending a MOCK Answer for a Treatment plan result to simulate answer : ");
		myRabbitMQProducerTest.sendTreatmentPlanResult(myPayload);
    	
    	Thread.sleep(2000);
    	
    	myNewAppointment=this.myAppointmentService.getAppointment(myPayload.getId());
		
		assertTrue(myNewAppointment.getState()==AppointmentState.TREATMENT_APPROVED);
		
		assertTrue(myConsumerServiceTest.financialSaveMessageReception);
		
		//Meanwhile a new change came and set appointment to canceled
		Appointment myNewAppointmentEntity=myAppointmentRepo.findById(myNewAppointment.getId()).get();
		
		log.info("=============================  Setting Status CANCELED ============= ");
		myNewAppointmentEntity.setState(AppointmentState.CANCELED);
		myAppointmentRepo.saveAndFlush(myNewAppointmentEntity);
		
		
		log.info("=============================  Sending a MOCK Answer for a financial result and checl to simulate answer : ");
		myPayload=modelMapper.map(myNewAppointment,AppointmentPayloadDTO.class);
		myRabbitMQProducerTest.sendCheckAnswerToAppointment(myPayload);
		
		Thread.sleep(1000);
		
		myNewAppointment=this.myAppointmentService.getAppointment(myPayload.getId());
		
		Thread.sleep(1000);
		
		assertTrue(myConsumerServiceTest.financialCancelMessageReception);
		
		assertTrue(myConsumerServiceTest.treatmentCancelMessageReception);
		
	}
}
