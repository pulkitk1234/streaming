package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.java.model.Message;
import com.java.service.StreamingProducerService;

@RestController
public class StreamingController {
	

	@Autowired
	StreamingProducerService producerService;
	
	
//	@GetMapping("/get")
	@MessageMapping("/stream")
	@SendTo("/topic/streaming")
	public Message produceMessage(@RequestBody Message message) throws Exception {
		return producerService.produceMessage(message);
	}
//	
//	 @MessageMapping("/stream")
//	 @SendTo("/topic/streaming")
//	 public String greet(String message) throws InterruptedException {
//	        return message;
//	    }

}
