package com.java.serviceImpl;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.java.controller.StreamingController;
import com.java.exception.EmptyMessageException;
import com.java.model.AppConstants;
import com.java.model.Message;
import com.java.model.StreamEntity;
import com.java.service.StreamingProducerService;
import org.springframework.beans.factory.annotation.Value;

@Service
public class StreamingProducerServiceImpl implements StreamingProducerService {

	Logger logger = LoggerFactory.getLogger(StreamingProducerServiceImpl.class);


	@Autowired
	private KafkaTemplate<String, Object> template;

	@Value("${topic}")
	private String topic;


	/*
	 * This function returns the message object received from frontend via websocket.
	 * It also send the received message to kafka topic for further processing.
	 */
	@Override
	public Message produceMessage(Message message) {

		try {
			//validate message received  
			if (!message.getMessage().get().isBlank() && validInput(message.getMessage().get()) && message.getTime().get().length() != 0) {    
				logger.info("message: " + message);
				template.send(topic, message);
			} 
			else if (message.getMessage().get().isBlank()) {
				throw new EmptyMessageException(AppConstants.EMPTY_MESSAGE);
			} 
			else if(!validInput(message.getMessage().get())) {
				throw new EmptyMessageException(AppConstants.INVALID_MESSAGE);
			}
			else {
				throw new EmptyMessageException(AppConstants.EMPTY_TIMESTAMP);
			}
		} 
		catch (Exception e) {

			e.printStackTrace();
		}

		return message;

	}
	
	public boolean validInput(String str) {
		    
		return str.chars()
	            .allMatch(Character::isLetter);
	}



}
