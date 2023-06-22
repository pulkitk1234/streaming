package com.java.controller;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.java.model.Message;
import com.java.model.StreamEntity;
import com.java.repository.StreamingRepository;
import com.java.service.StreamingService;
import com.java.serviceImpl.StreamingServiceImpl;

@RestController
public class StreamingController {
	
	
	@Autowired
	StreamingService service;
	
	@Autowired
	StreamingRepository repository;
	
	@MessageMapping("/stream")
    @SendTo("/topic/streaming")
	public Message sendMessage(@Payload Message message) {
		System.out.println("message "+message);
		return service.communicate(message);
		
	}
	
	@GetMapping("/get")
	public List<StreamEntity> getAllMessages(){
		return repository.findAll();
	}

}
