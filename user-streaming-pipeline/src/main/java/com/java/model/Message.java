package com.java.model;

import java.util.Optional;

import org.springframework.stereotype.Component;

//
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Message {
	
	private String message;
	private String time;
	
	public Optional<String> getMessage() {
		return Optional.ofNullable(message);
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Optional<String> getTime() {
		return Optional.ofNullable(time);
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Message(String text, String time) {
		super();
		this.message = text;
		this.time = time;
	}
	public Message(String htmlEscape) {
		this.message=htmlEscape;
	}
	public Message() {
		super();
	}
	
	
	

}