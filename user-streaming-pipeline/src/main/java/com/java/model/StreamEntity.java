package com.java.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StreamEntity {
	
	@Id
	@GeneratedValue
	private long id;
	private String text;
	private String date;
	private int longest_palindrom_length;
	

}