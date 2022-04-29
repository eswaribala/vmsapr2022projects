package com.vms.circuitbreakerapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vms.circuitbreakerapi.handlers.CBHandler;
import com.vms.circuitbreakerapi.models.Order;

@RestController
@RequestMapping("/cbhome")
public class CBController {
    @Autowired
	private CBHandler cbHandler;
	@PostMapping({"/v1.0"})
    public ResponseEntity<?> callCbHandler(@RequestBody Order order){
        return ResponseEntity.status(HttpStatus.OK).body( this.cbHandler.requestHandler(order)	);
    }
    
}
