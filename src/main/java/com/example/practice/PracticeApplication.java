package com.example.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.practice.Dao.MongoDAO;

@SpringBootApplication
@RestController
public class PracticeApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(PracticeApplication.class, args);		
	System.out.println(new MongoDAO().getMogoCollectionById("AllocationMeta", "0"));
	}
	
	
	@GetMapping("/test")
	public String helloWorld() {
		return "Hello World";
	}
	
	
	
}
