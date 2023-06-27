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
	
	@Override
	public Message produceMessage(Message message) {
		StreamEntity entity = new StreamEntity();
	    
	    	
	    	if(!message.getMessage().isBlank() && message.getTime().length()!=0) {	    		 entity.setText(message.getMessage());
	    		 entity.setDate(message.getTime());
	    		 template.send(topic, message);
	    	}
	    	else if(message.getMessage().isBlank()) {
	    		throw new EmptyMessageException("message cannot be empty or blank");
	    	}
	    	else {
	    		throw new EmptyMessageException("timestamp is not presnt");
	    	}
		entity.setText(message.getMessage());
		entity.setDate("demo");
		template.send(topic, message);
		
	    return message;
	   
	}

	
	
}
