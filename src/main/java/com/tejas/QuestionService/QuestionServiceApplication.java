package com.tejas.QuestionService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class QuestionServiceApplication {
//	My name is Tejas
	public static void main(String[] args) {
		SpringApplication.run(QuestionServiceApplication.class, args);
	}

}
