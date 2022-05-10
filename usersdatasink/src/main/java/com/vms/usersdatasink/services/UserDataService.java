package com.vms.usersdatasink.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.vms.usersdatasink.models.UserData;
//import com.vms.usersdatasink.repositories.UserDataRepo;
import com.vms.usersdatasink.repositories.UserDataRepo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
@Service
@Data
@Slf4j
public class UserDataService {
   @Autowired
	private UserDataRepo userDataRepo;
   private Gson gson;
   public void addUserDataList(List<String> userDataList) {
	   //log.info(userDataList.get(0).toString());
	   if(userDataList.size()>0) {
	  	  for(String userObj : userDataList) {
		    this.userDataRepo.save(new UserData(userObj));
	     }
	   }
   }
   
}
