package com.vms.paymentapi.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.vms.paymentapi.models.Order;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Data
public class OrderConsumerService {

   private Gson gson;
   private Order order;

    @KafkaListener(topics = "${order.topic.name}", 
			groupId = "${order.topic.group.id}")

	public void getOrderData(String message) {
		log.info("Order Details Rececived"+ message);
		gson=new Gson();
		order = gson.fromJson(message, Order.class);
		log.info("Order"+order);
	}
    
    
    
}
