package com.vms.orderapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.vms.orderapi.facades.OrderFacade;
import com.vms.orderapi.models.Order;

@Service
public class OrderService {
    @Autowired
	private OrderFacade orderFacade;
    
    
    public boolean publishOrder(Order order) {
    	
    	  MessageChannel msgChannel=this.orderFacade.outputChannel();
    	  
    	 return msgChannel.send(MessageBuilder
                 .withPayload(order)
                 .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                 .build());
    }
}
