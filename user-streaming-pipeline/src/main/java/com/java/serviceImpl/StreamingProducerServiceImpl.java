package com.java.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.java.exception.EmptyMessageException;
import com.java.model.Message;
import com.java.model.StreamEntity;
import com.java.service.StreamingProducerService;

@Service
public class StreamingProducerServiceImpl implements StreamingProducerService {
	
	@Autowired
	private KafkaTemplate<String, Object> template;

	private String topic = "stream1";
	
	
	/*
	 * This function returns the message object received from frontend via websocket.
	 * It also send the received message to kafka topic for further processing.
	 */
	@Override
	public Message produceMessage(Message message) {
		

	    	 //validate message received   	
	    	if(!message.getMessage().get().isBlank() && message.getTime().get().length()!=0) {	 
	    		 template.send(topic, message);
	    	}
	    	else if(message.getMessage().get().isBlank()) {
	    		throw new EmptyMessageException("message cannot be empty or blank");
	    	}
	    	else {
	    		throw new EmptyMessageException("timestamp is not presnt");
	    	}
		
	    return message;
	   
	}

	
	
}
