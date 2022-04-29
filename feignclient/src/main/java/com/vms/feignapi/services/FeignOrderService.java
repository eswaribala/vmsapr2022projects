package com.vms.feignapi.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.vms.feignapi.models.Order;

@FeignClient(name="Order-API")

public interface FeignOrderService {
	
	   @PostMapping("/orders/v1.0")
	    public ResponseEntity<String> publishOrder(Order order);



}
