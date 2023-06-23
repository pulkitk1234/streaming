package com.java.controller;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.model.Message;
import com.java.model.StreamEntity;

@RestController
public class StreamingController {
	
	@Autowired
	private KafkaTemplate<String, Object> template;
	
	private String topic = "stream1";
	
	@GetMapping("/get")
	public Message newF(@RequestBody Message message) {
		
		StreamEntity entity=new StreamEntity();
		entity.setDate(message.getTime());
		entity.setText(message.getMessage());
		template.send(topic, message);
		return new Message(message.getMessage(),message.getTime());
	}

}
