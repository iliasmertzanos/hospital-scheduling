package com.hospital.appointment.configuration.rabbitmq;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

@Configuration
public class RabbitMQExchangeConfiguration {
	
	@Value("${exchange.treatment}")
	private String directTreatmentExchange;
	
	@Value("${exchange.financial}")
	private String directFinancialExchange;
	
	@Bean
	public DirectExchange directFinancialExchange() {
	    return new DirectExchange(directFinancialExchange);
	}
	
	@Bean
	public DirectExchange directTreatmentExchange() {
	    return new DirectExchange(directTreatmentExchange);
	}
	    
}
