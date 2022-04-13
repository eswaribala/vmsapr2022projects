package com.citi.customerapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citi.customerapi.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{

	//custom queries
}
