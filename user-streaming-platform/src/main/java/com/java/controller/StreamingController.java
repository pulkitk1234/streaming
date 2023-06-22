package com.java.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.java.model.Message;

@RestController
public class StreamingController {
	
	@MessageMapping("/stream")
    @SendTo("/topic/streaming")
	public Message sendMessage(@RequestBody Message message) {
		
//		return new Message(
//                HtmlUtils.htmlEscape(message.getMessage()));
		return new Message(message.getMessage());
		
		
	}

}
