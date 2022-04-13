package com.citi.customerapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.citi.customerapi.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{

	//custom queries
	@Query("select customer from Customer customer where customer.name.firstName=:customerFirstName")
	public List<Customer> findByCustomerFirstName(@Param("customerFirstName") String customerFirstName);

	//join query related and unrelated tables
	
}
