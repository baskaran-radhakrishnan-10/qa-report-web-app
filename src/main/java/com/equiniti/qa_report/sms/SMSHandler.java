package com.equiniti.qa_report.sms;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plivo.helper.api.client.RestAPI;
import com.plivo.helper.api.response.message.MessageResponse;
import com.plivo.helper.exception.PlivoException;

@Service("smsService")
public class SMSHandler {

	@Autowired
	private SMSConfig smsConfig;

	public void sendSMS(SMSBean smsBean){
		RestAPI restAPI=smsConfig.getRestAPI();
		try {
			LinkedHashMap<String, String> paramMap=(LinkedHashMap<String, String>) returnSmsParam(smsBean);
			MessageResponse messageResponse=restAPI.sendMessage(paramMap);
			System.out.println(messageResponse.apiId);
			System.out.println(messageResponse.message);
			System.out.println(messageResponse.messageUuids);
		} catch (PlivoException e) {
			e.printStackTrace();
		}
	}

	private Map<String,String> returnSmsParam(SMSBean smsBean){
		Map<String,String> paramMap=new LinkedHashMap<>();
		if(null != smsBean.getSmsFrom() && !smsBean.getSmsFrom().isEmpty()){
			paramMap.put("src", smsBean.getSmsFrom());
		}
		if(null != smsBean.getSmsTo() && !smsBean.getSmsTo().isEmpty()){
			paramMap.put("dst", smsBean.getSmsTo());
		}
		if(null != smsBean.getMessage() && !smsBean.getMessage().isEmpty()){
			paramMap.put("text", smsBean.getMessage());
		}
		if(null != smsBean.getCallBackUrl() && !smsBean.getCallBackUrl().isEmpty()){
			paramMap.put("url", smsBean.getCallBackUrl());
		}
		if(null != smsBean.getRequestType() && !smsBean.getRequestType().isEmpty()){
			paramMap.put("method", smsBean.getRequestType());
		}
		return paramMap;
	}

}
