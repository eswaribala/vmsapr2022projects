package com.vms.usersdataprocessor.configurations;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.springframework.cloud.stream.messaging.Processor;


import org.springframework.cloud.stream.annotation.EnableBinding;

import org.springframework.context.annotation.Configuration;



import com.vms.usersdataprocessor.models.UserData;

import lombok.extern.slf4j.Slf4j;

import org.springframework.integration.annotation.ServiceActivator;


@Configuration
@EnableBinding(Processor.class)
@Slf4j
public class UserDataProcessorConfiguration {

	  private List<String> userDataList;
	 private  Map.Entry<String, Object> mapEntry;
	private   Iterator itr;
	
	@ServiceActivator(inputChannel = Processor.INPUT,outputChannel = Processor.OUTPUT)
	
	public List<String> transformMessage(Map<String,Object> userData)
	{
		
		
		    
		 Map<String,Object> ids = userData.entrySet() 
		          .stream() 
		          .filter(map -> map.getKey().equals("id")) 
		          .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));  
		 Map<String,String> names = userData.entrySet() 
		          .stream() 
		          .filter(map -> map.getKey().equals("name")) 
		          .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue().toString()));  
		 
		 Map<String,Object> emails = userData.entrySet() 
		          .stream() 
		          .filter(map -> map.getKey().equals("email")) 
		          .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));  
            
		 return new ArrayList<String>(names.values());
		 
		
     

	}
	
	

}
