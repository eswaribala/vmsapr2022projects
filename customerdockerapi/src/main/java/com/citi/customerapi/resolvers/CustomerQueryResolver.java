package com.citi.customerapi.resolvers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.citi.customerapi.models.Customer;
import com.citi.customerapi.services.CustomerService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
@Component
public class CustomerQueryResolver implements GraphQLQueryResolver{
	@Autowired
	private CustomerService customerService;
	
    public List<Customer> findAllCustomers(){
    	return this.customerService.getAllCustomers();
    }
	
	public Customer findCustomer(long customerId) {
		return this.customerService.getCustomerById(customerId);
	}
    
}
