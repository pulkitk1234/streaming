package com.java.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.java.model.Message;
import com.java.model.StreamEntity;

@SpringBootTest
class StreamingControllerTest {

	@Test
	public void produceMessage() {
		Message message= new Message();
		message.setMessage("aabbcc");
		message.setTime("2023-06-23 10:50:59.470432");
		Assertions.assertThat(message.getMessage().get().length()).isGreaterThan(0);
		}

}
