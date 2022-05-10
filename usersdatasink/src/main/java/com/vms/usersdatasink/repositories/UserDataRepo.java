package com.vms.usersdatasink.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vms.usersdatasink.models.UserData;

public interface UserDataRepo extends MongoRepository<UserData,String> {

}
