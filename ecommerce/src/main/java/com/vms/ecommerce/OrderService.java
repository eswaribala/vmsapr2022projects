package com.vms.ecommerce;

import java.time.LocalDate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.vms.ecommerce.models.Order;
@Component("orderService")
public class OrderService implements JavaDelegate {
    @Autowired
	private RestTemplate restTemplate;
    @Value("${orderApiUrl}")
    private String orderApiUrl;
    //@Value("${expectedResponse}")
   // private String expectedResponse;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		Order order =new Order();
	    order.setOrderId(Long.parseLong(execution.getVariable("orderId").toString()));
	    order.setOrderDate(LocalDate.parse(execution.getVariable("orderDate").toString()));
	    order.setOrderAmount(Long.parseLong(execution.getVariable("orderAmount").toString()));
	    HttpHeaders headers = new HttpHeaders();
	       headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity request = new HttpEntity<>(order,headers);
	    ResponseEntity<?> response=restTemplate.
	 		      postForEntity(orderApiUrl,request, String.class);
	    execution.setVariable("response", response.getBody());
		
	}

}
