package com.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class UserStreamingPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserStreamingPlatformApplication.class, args);
	}

}
