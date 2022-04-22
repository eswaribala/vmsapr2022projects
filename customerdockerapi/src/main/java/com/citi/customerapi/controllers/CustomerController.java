package com.citi.customerapi.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citi.customerapi.models.Customer;
import com.citi.customerapi.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	private Gson gson;
	@Value("${query}")
	private String query;
	//insert

	@PostMapping({"/v1.0"})
	public ResponseEntity<String> addCustomer(@RequestBody Customer customer){
		Customer customerObj=   this.customerService.addCustomer(customer);
	   gson=new Gson();
		
		if(customerObj!=null) 
		   return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(customerObj));
		else
			  return ResponseEntity.status(HttpStatus.OK).body("Customer not added");
	}
	
	@GetMapping({"/v1.0"})
	public List<Customer> getAllCustomers(){
		log.info(query);
		return this.customerService.getAllCustomers();
	}
	
	@GetMapping({"/v1.0/{customerId}"})
	public ResponseEntity<String> getCustomerById(@PathVariable("customerId") long customerId){
		Customer customerObj=   this.customerService.getCustomerById(customerId);
	    gson=new Gson();
		
		if(customerObj!=null) 
		   return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(customerObj));
		else
			  return ResponseEntity.status(HttpStatus.OK).body("Customer not found");
	}
	
	@DeleteMapping({"/v1.0/{customerId}"})
	public ResponseEntity<String> deleteCustomerById(@PathVariable("customerId") long customerId){
			
		if(this.customerService.deleteCustomerById(customerId)) 
		   return ResponseEntity.status(HttpStatus.OK).body("Customer Deleted");
		else
			  return ResponseEntity.status(HttpStatus.OK).body("Customer not found");
	}
	
	@PutMapping({"/v1.0/{customerId}/{dob}"})
	public ResponseEntity<String> updateCustomerById(@PathVariable("customerId") long customerId,
			@PathVariable("dob") String dob){
			
		LocalDate dateObj=LocalDate.parse(dob);
		Customer customerObj= this.customerService.updateCustomer(customerId, dateObj);
	    gson=new Gson();
		
		if(customerObj!=null) 
		   return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(customerObj));
		else
			  return ResponseEntity.status(HttpStatus.OK).body("Customer not found");
	}
	
	
	//squiggly filter
	
	@GetMapping(value="/v1.0/filters/{firstName}")
	public ResponseEntity<String> getCustomerByFields(@PathVariable("firstName") String firstName,
			@RequestParam(name = "fields", required = false) String fields){
			
    	
    	List<Customer> customers = this.customerService.getCustomerByFirstName(firstName);
    	
    	if(customers.size()>0)
    	{
    		//fields refers to runtime selection
    		ObjectMapper mapper = Squiggly.init(new ObjectMapper(), fields);  		
			return ResponseEntity.status(HttpStatus.OK).body(SquigglyUtils.stringify(mapper,customers.get(0)));

    	}
    	else
    	{
	         
		        
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Not found");
    	}

		
		
	}

	
	
}
