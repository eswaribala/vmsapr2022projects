package com.vms.usersdata.datagenerators;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.github.wnameless.json.flattener.JsonFlattener;
import com.github.wnameless.json.unflattener.JsonUnflattener;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;


@Service
public class UserDataService {
	
	@Autowired
	private RestTemplate restTemplate;
	@Value("${apiUrl}")
	private String apiUrl;
	
	public Map<String,Object>  getUserData() {
		
		ResponseEntity<String> responseEntity=restTemplate.exchange(apiUrl,HttpMethod.GET,null,String.class);

		return parseString(responseEntity.getBody());
		
	}
	

    public static Map<String, Object> parseString(String response) 
	{
    	JSONParser parser = new JSONParser();
    	Object obj;
    	String data="";
		String flattenedJson="";
		Map<String, Object> flattenedJsonMap =null;
		try {
			obj = parser.parse(response);
			JSONArray array = new JSONArray();
	    	array.add(obj);
			
		  	try {
		  		 
			
	 
				// JsonFlattener: A Java utility used to FLATTEN nested JSON objects
				flattenedJson = JsonFlattener.flatten(array.toString());
				//System.out.println("\n=====Simple Flatten===== \n" + flattenedJson);
	 
				flattenedJsonMap = JsonFlattener.flattenAsMap(array.toString());
			 	//data=(String) flattenedJsonMap.get("dataset");   
				//flattenedJsonMap.values().stream().forEach(System.out::println);
				//log("\n=====Flatten As Map=====\n" + flattenedJson);
				// We are using Java8 forEach loop. More info: https://crunchify.com/?p=8047
				//flattenedJsonMap.forEach((k, v) -> log(k + " : " + v));
	 
				// Unflatten it back to original JSON
				String nestedJson = JsonUnflattener.unflatten(flattenedJson);
				//System.out.println("\n=====Unflatten it back to original JSON===== \n" + nestedJson);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
	 return flattenedJsonMap;

	}

}
