package com.java.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.java.model.StreamEntity;
import com.java.repository.StreamingRepository;
import com.java.service.StreamingServiceConsumer;

import java.util.*;

@SpringBootTest
class StreamingConsumerTest {
	
	@Autowired
	StreamingRepository repo;
	
	@Test
	public void findAllEntries() {
		List<StreamEntity> list=repo.findAll();
		Assertions.assertThat(list.size()).isGreaterThan(0);
	}
	
	@Test
	public void communicate() {
		StreamEntity entity= new StreamEntity();
		entity.setDate("aaabbbbc");
		entity.setDate("2023-06-23 10:50:59.470432");
		entity.setLongest_palindrom_length(4);
		repo.save(entity);
		Assertions.assertThat(entity.getId()).isGreaterThanOrEqualTo(0);
		}
		

	

}
