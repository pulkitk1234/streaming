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
	
	/**
	* @author Pulkit kaushik
	* @version 1.0
	*/
	@MessageMapping("/stream")
	@SendTo("/topic/streaming")
	public Message produceMessage(@RequestBody Message message) throws Exception {
//		System.out.println("received messag is: "+message);
		return producerService.produceMessage(message);
	}


}
