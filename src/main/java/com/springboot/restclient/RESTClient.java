package com.springboot.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.springboot.model.UserModel;

@Component("restClient")
public class RESTClient {
	
	@Autowired
    private RestTemplate restTemplate;

	public UserModel[] getUserModels() {
		 
		return restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", UserModel[].class);
	}
}
