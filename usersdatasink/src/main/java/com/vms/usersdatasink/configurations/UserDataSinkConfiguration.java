package com.vms.usersdatasink.configurations;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;

import com.vms.usersdatasink.models.UserData;
import com.vms.usersdatasink.services.UserDataService;


@Configuration
@EnableBinding(Sink.class)
public class UserDataSinkConfiguration {
  @Autowired
   private UserDataService userDataService;
	 

	@StreamListener(Sink.INPUT)
	public void transformMessage(List<String> userData)
	{
			this.userDataService.addUserDataList(userData);

	}
	
	

}
