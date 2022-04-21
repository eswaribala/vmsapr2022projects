package com.citi.customerapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citi.customerapi.models.User;




public interface UserRepository extends JpaRepository<User,String>{

}
