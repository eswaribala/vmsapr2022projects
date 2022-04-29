package com.vms.stockapi.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.vms.stockapi.models.Order;
import com.vms.stockapi.models.Payment;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Data
public class PaymentConsumerService {

   private Gson gson;
   private Payment payment;

    @KafkaListener(topics = "${payment.topic.name}", 
			groupId = "${payment.topic.group.id}")

	public void getPaymentData(String message) {
		log.info("Payment Details Rececived"+ message);
		gson=new Gson();
		payment = gson.fromJson(message, Payment.class);
		log.info("Payment"+payment);
	}
    
    
    
}
