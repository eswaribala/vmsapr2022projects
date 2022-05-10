package com.vms.usersdata.configurations;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.core.MessageSource;

import com.google.gson.GsonBuilder;
import com.vms.usersdata.datagenerators.UserDataService;

import net.minidev.json.parser.ParseException;

import org.springframework.integration.annotation.Poller;
import org.springframework.integration.support.MessageBuilder;

@Configuration
@EnableBinding(Source.class)
public class UserDataConfiguration {
	
	   @Autowired
		private UserDataService userDataService; 
	   
	   @Bean
		@InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "100000", 
		maxMessagesPerPoll = "1"))
		public MessageSource<Map<String,Object>>  sendMessage() 
		{	
		   
			return ()->{
				return MessageBuilder.withPayload(userDataService.getUserData()).build();
				
			};
		}
		


	
	

}
