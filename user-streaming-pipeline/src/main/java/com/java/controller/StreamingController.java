package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.java.model.Message;
import com.java.service.StreamingProducerService;

@RestController
public class StreamingController {
	
	Logger logger = LoggerFactory.getLogger(StreamingController.class);
	
	@Autowired
	StreamingProducerService producerService;
	
	/**
	* @author Pulkit kaushik
	* @version 1.0
	*/
	@MessageMapping("/stream")
	@SendTo("/topic/streaming")
	public Message produceMessage(@RequestBody Message message) throws Exception {
		logger.info("received message is: "+message);
		return producerService.produceMessage(message);
	}


}
