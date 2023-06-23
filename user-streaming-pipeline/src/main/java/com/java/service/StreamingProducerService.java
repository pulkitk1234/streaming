package com.java.service;

import org.springframework.stereotype.Service;

import com.java.model.Message;

@Service
public interface StreamingProducerService {
	
	public Message produceMessage(Message message) throws Exception;

}
