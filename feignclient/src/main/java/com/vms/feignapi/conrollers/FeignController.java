package com.vms.feignapi.conrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vms.feignapi.models.Order;
import com.vms.feignapi.services.FeignOrderService;
import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController

public class FeignController {
    @Autowired
	private FeignOrderService feignOrderService;

     private Tracer tracer;
    
     public FeignController(Tracer tracer) {
    	 this.tracer=tracer;
     }
    
    private HttpStatus status;
    
    @PostMapping("/orders")
	public ResponseEntity<String> publishData(@RequestBody Order order){


        Span span = tracer.buildSpan("publish order....").start();
       ResponseEntity<String> response= this.feignOrderService.publishOrder(order);
       String data=response.getBody();

        if ((data!=null)&&(data.length()>1)) {
            status = HttpStatus.CREATED;
            span.setTag("http.status_code", 201);
        } else {
            span.setTag("http.status_code", 403);
        }

    	span.finish();
		return response;
	}
	
}
