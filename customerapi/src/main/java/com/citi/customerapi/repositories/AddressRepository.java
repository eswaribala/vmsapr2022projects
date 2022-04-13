package com.citi.customerapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citi.customerapi.models.Address;

public interface AddressRepository extends JpaRepository<Address,Long>{

}
