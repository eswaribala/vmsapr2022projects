package com.vms.circuitbreakerapi.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.vms.circuitbreakerapi.models.Order;


@Service
public class CBHandler {
	
	@Autowired
	private RestTemplate restTemplate;
   
    @Value("${serviceUrl}")
    private String serviceUrl;
    @Value("${fallBackUrl}")
    private String fallBackUrl;
    

    @HystrixCommand(commandKey = "GetTraderCommand", groupKey = "TraderGroupKey", threadPoolKey = "Test",
    		fallbackMethod = "fallbackRequestHandler",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
                    @HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "false")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "101"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
            })

   public ResponseEntity<?> requestHandler(Order order)
	{
    	HttpHeaders headers = new HttpHeaders();
	       headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity request = new HttpEntity<>(order,headers);
	    return restTemplate.postForEntity(serviceUrl,request, String.class);
	}
	
	public ResponseEntity<?> fallbackRequestHandler(Order order)
	{
		HttpHeaders headers = new HttpHeaders();
	       headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity request = new HttpEntity<>(order,headers);
		return restTemplate.postForEntity( fallBackUrl,request, String.class);
		
		
	}


}
