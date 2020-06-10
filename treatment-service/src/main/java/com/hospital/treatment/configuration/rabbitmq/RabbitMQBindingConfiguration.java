package com.hospital.treatment.configuration.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQBindingConfiguration {

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
    
    @Value("${routing.treatment.cancel}")
    private String routingTreatmentCancel;

//    @Bean
//    public Binding bindingFinancialExchangeToQueueResult(DirectExchange directFinancialExchange, Queue queueFinancialResult) {
//        return BindingBuilder.bind(queueFinancialResult).to(directFinancialExchange).with(routingFinancialResult);
//    }
//    
//    @Bean
//    public Binding bindingFinancialExchangeToQueueCheck(DirectExchange directFinancialExchange, Queue queueFinancialCheck) {
//        return BindingBuilder.bind(queueFinancialCheck).to(directFinancialExchange).with(routingFinancialCheck);
//    }
//    
//    @Bean
//    public Binding bindingFinancialExchangeToQueueSave(DirectExchange directFinancialExchange, Queue queueFinancialSave) {
//        return BindingBuilder.bind(queueFinancialSave).to(directFinancialExchange).with(routingFinancialSave);
//    }
//    
//    @Bean
//    public Binding bindingFinancialExchangeToQueueCancel(DirectExchange directFinancialExchange, Queue queueFinancialCancel) {
//        return BindingBuilder.bind(queueFinancialCancel).to(directFinancialExchange).with(routingFinancialCancel);
//    }
//    
//    @Bean
//    public Binding bindingTreatmentExchangeToQueueResult(DirectExchange directTreatmentExchange, Queue queueTreatmentResult) {
//        return BindingBuilder.bind(queueTreatmentResult).to(directTreatmentExchange).with(routingTreatmentResult);
//    }
//    
//    @Bean
//    public Binding bindingTreatmentExchangeToQueueRequest(DirectExchange directTreatmentExchange, Queue queueTreatmentRequest) {
//        return BindingBuilder.bind(queueTreatmentRequest).to(directTreatmentExchange).with(routingTreatmentRequest);
//    }
//    
//    @Bean
//    public Binding bindingTreatmentExchangeToQueueCancel(DirectExchange directTreatmentExchange, Queue queueTreatmentCancel) {
//        return BindingBuilder.bind(queueTreatmentCancel).to(directTreatmentExchange).with(routingTreatmentCancel);
//    }
    


}
