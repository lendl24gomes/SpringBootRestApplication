package com.springboot.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@ComponentScan("com.springboot")
@SpringBootApplication
public class SpringBootWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebServiceApplication.class, args);
	}
	
	@Bean
    public RestTemplate geRestTemplate() {
        return new RestTemplate();
    }
}
