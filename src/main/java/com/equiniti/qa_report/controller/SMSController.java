package com.equiniti.qa_report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equiniti.qa_report.queue.connector.SMSQueueConnector;
import com.equiniti.qa_report.sms.SMSBean;

@Service("smsController")
public class SMSController {
	
	@Autowired
	private SMSQueueConnector smsQueueConnector;

	public void sendOneTimePassword(String senderMobNo,String recMobNo,String otp){
		sendSMS(senderMobNo,recMobNo,otp);
	}
	
	private void sendSMS(String senderMobNo,String RecMobNo,String otp){
		SMSBean bean=new SMSBean();
		bean.setSmsFrom(senderMobNo);
		bean.setSmsTo(RecMobNo);
		bean.setMessage(otp);
		bean.setRequestType("GET");
		smsQueueConnector.produce(bean);
	}

}
