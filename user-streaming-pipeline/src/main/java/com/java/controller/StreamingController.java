package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.java.model.Message;
import com.java.service.StreamingProducerService;

@RestController
public class StreamingController {
	

	@Autowired
	StreamingProducerService producerService;
	
	@GetMapping("/get")
	public Message produceMessage(@RequestBody Message message) throws Exception {
		
		return producerService.produceMessage(message);
	}

}
