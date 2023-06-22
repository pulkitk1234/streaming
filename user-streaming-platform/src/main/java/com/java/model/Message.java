package com.java.model;

import org.springframework.stereotype.Component;

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
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTime() {
		return time;
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
		// TODO Auto-generated constructor stub
	}
	public Message() {
		super();
	}
	
	
	

}
