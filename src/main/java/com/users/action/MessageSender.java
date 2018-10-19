package com.users.action;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	public Object sendMessage(String exchange, String routingKey, String message) {
		return rabbitTemplate.convertSendAndReceive(exchange, routingKey, message );
	}
	
}
