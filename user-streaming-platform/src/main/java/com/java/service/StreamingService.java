package com.java.service;

import org.springframework.stereotype.Service;

import com.java.model.Message;

@Service
public interface StreamingService {

	Message communicate(Message message);

}
