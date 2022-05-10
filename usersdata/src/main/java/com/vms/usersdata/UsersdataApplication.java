package com.vms.usersdata;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vms.usersdata.datagenerators.UserDataService;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class UsersdataApplication {
//implements CommandLineRunner {
	@Autowired
 private UserDataService userDataService;
	public static void main(String[] args) {
		SpringApplication.run(UsersdataApplication.class, args);
		
	}
	
	@Bean
	RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	/*
	  @Override public void run(String... args) throws Exception { 
		  // TODO   Auto-generated method stub 
		 Iterator itr=userDataService.getUserData().entrySet().iterator();
		 Map.Entry<String, Object> mapEntry;
          while(itr.hasNext()) {
          	mapEntry=(Map.Entry<String, Object>) itr.next();
          	log.info(mapEntry.getKey());
          	log.info(mapEntry.getValue().toString());
          }
	  }
	  */
}
