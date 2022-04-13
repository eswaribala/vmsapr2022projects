package com.citi.customerapi.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.customerapi.models.Customer;
import com.citi.customerapi.repositories.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
	private CustomerRepository customerRepository;
    
    
    //insert
    public Customer addCustomer(Customer customer) {
    	return this.customerRepository.save(customer);
    }
    
    //select all
    public List<Customer> getAllCustomers(){
    	return this.customerRepository.findAll();
    }
    
    //select by id
    
    public Customer getCustomerById(long customerId) {
    	return this.customerRepository.findById(customerId).orElse(null);
    	
    }
    
    //update
    
    public Customer updateCustomer(long customerId, LocalDate dob) {
    	Customer customer=this.getCustomerById(customerId);
    	if(customer!=null) {
    		customer.setDob(dob);
    		this.customerRepository.save(customer);
    	}
    	return customer;  		
    	
    }
    
    //delete
	
    public boolean deleteCustomerById(long customerId) {
    	this.customerRepository.deleteById(customerId);
    	if(this.getCustomerById(customerId)==null)
    		return true;
    	else
    		return false;
    }
	
	
}
