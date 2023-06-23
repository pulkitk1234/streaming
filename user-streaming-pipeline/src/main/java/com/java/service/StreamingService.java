package com.java.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java.model.Message;
import com.java.model.StreamEntity;

@Service
public interface StreamingService {

	Message communicate(Message message);

	List<StreamEntity> getALlMessages();

}
