package com.hospital.treatment.configuration.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQQueueConfiguration {
    @Value("${queue.financial.result}")
    private String queueFinancialResult;

    @Value("${queue.treatment.result}")
    private String queueTreatmentResult;

    @Value("${queue.financial.check}")
    private String queueFinancialCheck;

    @Value("${queue.financial.save}")
    private String queueFinancialSave;

    @Value("${queue.financial.cancel}")
    private String queueFinancialCancel;

    @Value("${queue.treatment.request}")
    private String queueTreatmentRequest;
    
    @Value("${queue.treatment.cancel}")
    private String queueTreatmentCancel;
//
//    @Bean
//    public Queue queueFinancialResult() {
//        return new Queue(queueFinancialResult);
//    }
//
//    @Bean
//    public Queue queueFinancialCheck() {
//        return new Queue(queueFinancialCheck);
//    }
//
//    @Bean
//    public Queue queueFinancialSave() {
//        return new Queue(queueFinancialSave);
//    }
//
//    @Bean
//    public Queue queueFinancialCancel() {
//        return new Queue(queueFinancialCancel);
//    }
//
//    @Bean
//    public Queue queueTreatmentRequest() {
//        return new Queue(queueTreatmentRequest);
//    }
//
//    @Bean
//    public Queue queueTreatmentResult() {
//        return new Queue(queueTreatmentResult);
//    }
//    
//    @Bean
//    public Queue queueTreatmentCancel() {
//        return new Queue(queueTreatmentCancel);
//    }
}
