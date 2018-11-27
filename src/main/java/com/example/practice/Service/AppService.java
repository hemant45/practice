package com.example.practice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.example.practice.Dao.AppDAO;

@Service
@EnableConfigurationProperties
public class AppService {
	
	@Autowired
	AppDAO appdao;
	
	public void getAppDetails() {
		appdao.getAppDetails();
	}
	
	
	
	
	
	
	
	
	
	
}
