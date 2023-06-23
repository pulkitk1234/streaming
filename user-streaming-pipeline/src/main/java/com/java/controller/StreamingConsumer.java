package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.model.Message;
import com.java.model.StreamEntity;
import com.java.repository.StreamingRepository;
import com.java.service.StreamingService;

@RestController
public class StreamingConsumer {
	
//	Message message2;
//	
//	@Autowired
//	StreamingRepository repository;
	
	@Autowired
	StreamingService service;
	
//	@GetMapping("/consume")
//	public Message consumed() {
//		return message2;
//	}
	
	@KafkaListener(groupId = "group-1", topics = "stream1", containerFactory = "userKafkaListenerContainerFactory")
	public Message getJsonMsgFromTopic(Message message) {
		
		return service.communicate(message);
		
//		StreamEntity entity= new StreamEntity();
//		entity.setDate(message.getTime());
//		entity.setText(message.getMessage());
//		entity.setLongest_palindrom_length(2);
//		repository.save(entity);
//		System.out.println("saved entry is : "+entity);
//		message2 = message;
//		return message2;
	}

}
