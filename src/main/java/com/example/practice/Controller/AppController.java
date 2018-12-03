package com.example.practice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.practice.Service.AppService;


@RestController
public class AppController {
	@Autowired
	AppService appservice;
	
	@GetMapping("/appDetails")
	public String getAppDetails() throws InterruptedException {
		appservice.getAppDetails();
		return "Processed";
	}

}
