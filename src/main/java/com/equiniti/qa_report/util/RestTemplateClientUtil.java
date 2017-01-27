package com.equiniti.qa_report.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

public class RestTemplateClientUtil {
	
	public static void main(String args[]){
		
		final String uri = "http://localhost:8080/springrestexample/employees/{id}";
	     
	    Map<String, String> inputObj = new HashMap<String, String>();
	    
	    inputObj.put("phone_number", "+919790610201");
	    inputObj.put("phone_number_country", "IN");
	    inputObj.put("type", "SMS");
	    inputObj.put("csrfmiddlewaretoken", "0e81205d022ed8a89daa98fd9dc40863");
	     
	    RestTemplate restTemplate = new RestTemplate();
	    
	    Object returnObj=null;
		
		returnObj = restTemplate.postForObject("https://manage.plivo.com/sandbox-numbers/add",inputObj,Map.class,inputObj);
		
	    System.out.println(returnObj);
	}

}
