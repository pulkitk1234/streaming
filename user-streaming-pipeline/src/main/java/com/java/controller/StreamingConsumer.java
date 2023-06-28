package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.java.model.Message;
import com.java.model.StreamEntity;
import com.java.service.StreamingServiceConsumer;
import java.util.*;

/**
* @author Pulkit kaushik
* @version 1.0
*/
@RestController
public class StreamingConsumer {
	
	
	@Autowired
	StreamingServiceConsumer service;
	
	/**
	* kafka consumer to consume and persist the data in db. 
	*/
	@KafkaListener(groupId = "group-1", topics = "stream1", containerFactory = "userKafkaListenerContainerFactory")
	public Message getJsonMsgFromTopic(Message message) {
		
		return service.communicate(message);
		
	}
	
	/**
	* api to get all entries from db. 
	*/
	@GetMapping("/getEntries")
	public List<StreamEntity> findAllEntries(){
		return service.findAllEntries();
	}

}
