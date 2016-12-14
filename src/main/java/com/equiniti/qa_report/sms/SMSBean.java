package com.equiniti.qa_report.sms;

import java.io.Serializable;

public class SMSBean implements Serializable{

	private static final long serialVersionUID = 270190442213149368L;
	
	private String smsFrom=null;
	
	private String smsTo=null;
	
	private String message=null;
	
	private String callBackUrl=null;
	
	private String requestType=null;

	public String getSmsFrom() {
		return smsFrom;
	}

	public void setSmsFrom(String smsFrom) {
		this.smsFrom = smsFrom;
	}

	public String getSmsTo() {
		return smsTo;
	}

	public void setSmsTo(String smsTo) {
		this.smsTo = smsTo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCallBackUrl() {
		return callBackUrl;
	}

	public void setCallBackUrl(String callBackUrl) {
		this.callBackUrl = callBackUrl;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
 
}
