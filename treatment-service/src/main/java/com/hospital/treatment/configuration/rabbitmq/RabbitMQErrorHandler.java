package com.hospital.treatment.configuration.rabbitmq;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

import com.hospital.treatment.events.consumer.ConsumerService;
import com.hospital.treatment.events.producer.ProducerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class RabbitMQErrorHandler implements ErrorHandler
{
	private final ProducerService myProducer;

    @Override
    public void handleError(Throwable t) {
        System.out.println("======================================================================================");
        System.out.println("error occurred in message listener and handled in error handler" + t.toString());
        System.out.println("======================================================================================");
        
        throw new AmqpRejectAndDontRequeueException("Error Handler converted exception to fatal", t);
        
    }
}