package com.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.UserModel;
import com.springboot.restclient.RESTClient;
import com.springboot.services.ImageCreationService;


@RestController
public class RESTController {
	
	@Autowired
	private RESTClient restClient;
	
	@Autowired
	private ImageCreationService ics;
	
	@Autowired
	private Environment envt;

	@RequestMapping(value = "/assignment", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserModel[]> getAssignment() {	
		UserModel[] userm = restClient.getUserModels();			
		for(int i=0; i<userm.length; i++) 
		{
		userm[i].setTitle(new StringBuffer(userm[i].getTitle()).reverse().toString());  
		userm[i].setBody(new StringBuffer(userm[i].getBody()).reverse().toString());  
		}	
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(userm);
	}
	
	@RequestMapping(value = "/ingest", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getImage(@RequestBody String input) throws IOException {
		String filePath = ics.textToImage(input);
		String imgUrl = "{\"Image Link\" : \"" +  filePath + "\"}";
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(imgUrl);

	}
	
	@RequestMapping(value = "/image", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> showImage() throws IOException  {
		File image = new File(envt.getProperty("fileLocation"));
		byte[] file = Files.readAllBytes(image.toPath());
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(file);
	}
	
}
