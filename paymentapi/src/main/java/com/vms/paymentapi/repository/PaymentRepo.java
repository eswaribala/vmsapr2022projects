package com.vms.paymentapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vms.paymentapi.models.Payment;

public interface PaymentRepo extends MongoRepository<Payment,Long>{

}
