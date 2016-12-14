package com.equiniti.qa_report.sms;

import com.plivo.helper.api.client.RestAPI;

public class SMSConfig {

	private RestAPI restAPI=null;
	
	public SMSConfig(String authId,String authToken,String version){
		this.restAPI=new RestAPI(authId, authToken, version);
	}

	public RestAPI getRestAPI() {
		return restAPI;
	}

}
