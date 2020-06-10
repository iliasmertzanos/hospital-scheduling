package com.hospital.treatment;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hospital.payloads.AppointmentPayloadDTO;
import com.hospital.treatment.enums.ClinicalArea;
import com.hospital.treatment.repositories.TreatmentPlanRepo;
import com.hospital.treatment.services.DoctorService;
import com.hospital.treatment.services.MessageHandler;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = TreatmentServiceApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
class TreatmentServiceApplicationUnitTester {

	@Autowired
	private MessageHandler myMessageHandler;
	
	@Autowired
	private DoctorService myDoctorService;
	
	@Autowired
	private TreatmentPlanRepo mytrPlanRepo;
	
//	@Test
//	void test_message_from_appointment_received_clinic_dont_have_disease() {
//		AppointmentPayloadDTO myAppointmentPayloadDTO=new AppointmentPayloadDTO();
//		
//		myAppointmentPayloadDTO.setDisease("PARADONTITIS");
//		
////		assertNotNull(myMessageHandler.handleNewIncomingPlanRequest(myAppointmentPayloadDTO).getRejectionReason());
//		
//	}
	
	@Test
	public void test_doctor_service_find_doctor_list() {
		assertNotNull(myDoctorService.findDoctorsWithFreeSlots(1.0, ClinicalArea.PATHOLOGY).size()==1);
	}
	
	@Test
	void test_message_handler_saved_new_treatment_plan() {
		AppointmentPayloadDTO myAppointmentPayloadDTO=new AppointmentPayloadDTO();
		
		myAppointmentPayloadDTO.setDisease("COVID_19");
		myAppointmentPayloadDTO.setPatientId(2L);
		
		
		this.myMessageHandler.handleNewIncomingPlanRequest(myAppointmentPayloadDTO);
//		assertNotNull(mytrPlanRepo.find);
	}

}
