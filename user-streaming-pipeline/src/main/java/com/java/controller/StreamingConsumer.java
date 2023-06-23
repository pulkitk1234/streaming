package com.java.controller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.model.Message;
import com.java.model.StreamEntity;

@RestController
public class StreamingConsumer {
	
	StreamEntity message2;
	
	@GetMapping("/consume")
	public StreamEntity consumed() {
		return message2;
	}
	
	@KafkaListener(groupId = "javatechie-2", topics = "javatechie", containerFactory = "userKafkaListenerContainerFactory")
	public StreamEntity getJsonMsgFromTopic(StreamEntity message) {
		message2 = message;
		return message2;
	}

}
