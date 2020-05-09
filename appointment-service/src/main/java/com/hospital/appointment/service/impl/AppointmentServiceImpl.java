package com.hospital.appointment.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospital.appointment.dto.AppointmentDTO;
import com.hospital.appointment.entities.Appointment;
import com.hospital.appointment.entities.Patient;
import com.hospital.appointment.enums.AppointmentState;
import com.hospital.appointment.events.producer.ProducerService;
import com.hospital.appointment.repositories.AppointmentRepository;
import com.hospital.appointment.repositories.PatientRepository;
import com.hospital.appointment.service.AppointmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
	
	private final PatientRepository myPatientRepo;
	
	private final ProducerService myRabbitMQProducer;
	
	private final AppointmentRepository myAppointmentRepo;
	
	private  ModelMapper modelMapper=new ModelMapper() ;

	@Override
	@Transactional
	public AppointmentDTO handleNewIncomingAppointment(AppointmentDTO myAppointment) {		
		Appointment myNewAppointment=new Appointment();
		
		log.info(" ============================  New Appointment request received, prepearing data: "+myAppointment);
		
		Patient myExistingPatient=myPatientRepo.findById(myAppointment.getPatient().getId()).get();		
		
		log.info(" ============================ Data ready saving in data base appointment: ");
		Appointment mySavedAppointment=myAppointmentRepo.saveAndFlush(myNewAppointment);
		
		mySavedAppointment.setPatient(myExistingPatient);
		myExistingPatient.getAppointment().add(mySavedAppointment);
		myExistingPatient.setDisease(myAppointment.getPatient().getDisease());
		
		
		myPatientRepo.saveAndFlush(myExistingPatient);
		
		if(mySavedAppointment!=null) {
			log.info("============================ New Appointment saved, sending treatment plan request ...");
		}
		
		//request new treatment plan
		AppointmentDTO appointmentDTO=modelMapper.map(mySavedAppointment, AppointmentDTO.class);
		
		log.info("============================ Calling producer to send treatment plan Request ...");
		myRabbitMQProducer.sendToRequestNewTreatmentPlan(myAppointment);
		
		return appointmentDTO;
	}
	
	@Override
	@Transactional
//	(noRollbackFor = {org.hibernate.StaleObjectStateException.class,ObjectOptimisticLockingFailureException.class,org.hibernate.dialect.lock.OptimisticEntityLockException.class})
    public AppointmentDTO handleFinancialResultReception(AppointmentDTO myAppointment) {
    	log.info(" ============================  Message received in Queue queue-check-fin-result : " + myAppointment);
    	
    	log.info(" ============================   Checking if patient is financial approved after receiving answer from financial service....");
        //check if patient is financial covered
    	if(myAppointment.getPatient().isFinancialApproved()) {
    		
    		log.info("  ============================  Patient is ok");
    		log.info("  ============================  Set status of appointment to FINANCIAL_APPROVED and request a new treatment plan ....");
    		//if so set state to FINANCIAL_APPROVED
    		return changeAppointmentState(myAppointment, AppointmentState.APPROVED);
    		
    		//now send message to treatment service in order to get back
    		// a treatment plan (doctor, costs, room and clinic)
    	}else {
    		log.info(" ============================   Patient not ok");
    		log.info(" ============================   set status to REJECTED");
    		return changeAppointmentState(myAppointment, AppointmentState.REJECTED);
    	}
    }

	@Override
	@Transactional
	public AppointmentDTO handleFinancialResultFailure(AppointmentDTO myPayload) {
		
		log.info("============================ Calling producer to send Financial Cancel Bill ...");
		this.myRabbitMQProducer.sendToCancelBill(myPayload);
		return myPayload;
	}

	@Override
	@Transactional
	public AppointmentDTO handleTreatmentResultReception(AppointmentDTO myPayload) {
		log.info(" ============================  Message received in Queue queue-treatment-plan-result: " + myPayload);
    	
    	log.info(" ============================   Checking if treatment plan approved after receiving answer from treatment service....");
        //check if patient is financial covered
    	if(myPayload.getRejectionReason()==null) {
    		
    		log.info("  ============================  Appointment is ok");
    		log.info("  ============================  Set status of appointment to TREATMENT APPROVED and request a new treatment plan ....");
    		//if so set state to FINANCIAL_APPROVED
    		AppointmentDTO updatedAppointment= changeAppointmentState(myPayload, AppointmentState.TREATMENT_APPROVED);
    		
    		this.myRabbitMQProducer.sendToSaveBills(myPayload);
    		
    		return updatedAppointment;
    	}else {
    		log.info(" ============================   Patient not ok");
    		log.info(" ============================   set status to REJECTED");
    		return changeAppointmentState(myPayload, AppointmentState.REJECTED);
    	}
	}

	@Override
	@Transactional
	public AppointmentDTO handleTreatmentResultFailure(AppointmentDTO myAppointment) {
		log.info("============================ Calling producer to send Treatment plan cancelation ...");
		this.myRabbitMQProducer.sendToCancelTreatmentPlan(myAppointment);
		return myAppointment;
	}
	
    private AppointmentDTO changeAppointmentState(AppointmentDTO myAppointment, AppointmentState newState) {
		Appointment myExistingAppointment=modelMapper.map(myAppointment,Appointment.class);
		log.info(" ============================ Changing state to "+newState+" of Appointment:\n"+myExistingAppointment);
		myExistingAppointment.setState(newState);
		myExistingAppointment.setRejectionReason(myAppointment.getRejectionReason());
		return modelMapper.map(myAppointmentRepo.saveAndFlush(myExistingAppointment),AppointmentDTO.class);
	}

	@Override
	@Transactional
	public AppointmentDTO getAppointment(Long id) {
		Appointment myExistingAppointment= this.myAppointmentRepo.findById(id).orElse(null);
		
		if(myExistingAppointment==null)
			return null;
		return modelMapper.map(myExistingAppointment,AppointmentDTO.class);
	}
	
}
