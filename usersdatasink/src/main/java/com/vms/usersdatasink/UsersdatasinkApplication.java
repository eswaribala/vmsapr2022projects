package com.vms.usersdatasink;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import lombok.extern.slf4j.Slf4j;

@SpringBootApplication

public class UsersdatasinkApplication {
//implements CommandLineRunner {
 
	public static void main(String[] args) {
		SpringApplication.run(UsersdatasinkApplication.class, args);
		
	}
	
	@Bean
	RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	

}
