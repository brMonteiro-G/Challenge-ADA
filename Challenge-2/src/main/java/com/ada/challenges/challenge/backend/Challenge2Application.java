package com.ada.challenges.challenge.backend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Challenge1Application {

	public static void main(String[] args) {
		SpringApplication.run(Challenge1Application.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public ApplicationQueue applicationQueue() {
		return new ApplicationQueue(10);
	}

}
