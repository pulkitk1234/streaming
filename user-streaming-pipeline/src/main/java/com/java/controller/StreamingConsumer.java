package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.model.Message;
import com.java.model.StreamEntity;
import com.java.repository.StreamingRepository;
import com.java.service.StreamingServiceConsumer;
import java.util.*;
@RestController
public class StreamingConsumer {
	
	
	@Autowired
	StreamingServiceConsumer service;
	
//	@GetMapping("/consume")
//	public Message consumed() {
//		return message2;
//	}
	
	@KafkaListener(groupId = "group-1", topics = "stream1", containerFactory = "userKafkaListenerContainerFactory")
	public Message getJsonMsgFromTopic(Message message) {
		
		return service.communicate(message);
		
	}
	
	@GetMapping("/getEntries")
	public List<StreamEntity> findAllEntries(){
		return service.findAllEntries();
	}

}
