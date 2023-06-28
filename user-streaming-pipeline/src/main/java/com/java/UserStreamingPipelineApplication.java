package com.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserStreamingPipelineApplication {
	
//	refer -> https://kafka.apache.org/quickstart- for kafka refs
	
	//docker compose -f docker-compose.yml down // to stop containers
	//pwd-> /Users/pulkitkaushik/Desktop/brandwatch-repo/streaming/user-streaming-pipeline
	//	-> run: docker compose -f docker-compose.yml up -d (to start kafka)
	//pwd-> /Users/pulkitkaushik/Desktop/sts-projects
	//	-> run:docker compose -f docker-compose.yml up -d(to start postgresql)
	//start springboot app 


	public static void main(String[] args) {
	
		SpringApplication.run(UserStreamingPipelineApplication.class, args);
	}

}
