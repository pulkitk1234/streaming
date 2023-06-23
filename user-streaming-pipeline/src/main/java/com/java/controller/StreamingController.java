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
	
	private String topic = "javatechie";
	
	@GetMapping("/get")
	public Message newF(@RequestBody Message message) {
		
		StreamEntity entity=new StreamEntity();
		Timestamp instant= Timestamp.from(Instant.now());  
		String s1=String.valueOf(instant);
		System.out.println("date is "+s1);
		entity.setDate(s1);
		entity.setText(message.getMessage());
//		entity.setLongest_palindrom_length(palindrom_len);
//		repository.save(entity);
		template.send(topic, entity);
		return new Message(message.getMessage());
	}

}
