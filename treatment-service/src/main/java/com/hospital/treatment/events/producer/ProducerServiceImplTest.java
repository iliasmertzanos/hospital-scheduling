package com.hospital.treatment.events.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProducerServiceImplTest implements ProducerServiceTest {
	
	@Value("${routing.financial.result}")
    private String routingFinancialResult;

    @Value("${routing.financial.check}")
    private String routingFinancialCheck;
    
    @Value("${routing.financial.save}")
    private String routingFinancialSave;
    
    @Value("${routing.financial.cancel}")
    private String routingFinancialCancel;
    
    @Value("${routing.treatment.result}")
    private String routingTreatmentResult;
    
    @Value("${routing.treatment.request}")
    private String routingTreatmentRequest;

    @Value("${exchange.treatment}")
	private String directTreatmentExchange;
	
	@Value("${exchange.financial}")
	private String directFinancialExchange;

    private final RabbitTemplate rabbitTemplate;
    
   
}
