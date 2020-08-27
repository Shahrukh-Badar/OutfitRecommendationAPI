package com.weather.help;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@SpringBootApplication
public class MyApp {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	// Start spring boot app
	public static void main(String[] args) {
		SpringApplication.run(MyApp.class, args);
	}

}
