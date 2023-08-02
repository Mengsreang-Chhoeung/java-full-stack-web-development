package com.ms.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LearnSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringbootApplication.class, args);
	}

	@GetMapping("/greeting")
	public String getGreeting() {
		return "<h1>Hello <span style='color: red'>World</span></h1>";
	}

}
