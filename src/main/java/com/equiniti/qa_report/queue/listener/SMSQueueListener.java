package com.equiniti.qa_report.queue.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.equiniti.qa_report.sms.SMSBean;
import com.equiniti.qa_report.sms.SMSHandler;


public class SMSQueueListener implements MessageListener{

	@Autowired
	private SMSHandler smsService;

	@Override
	public void onMessage(Message message) {
		if(null != message && message instanceof ObjectMessage){
			ObjectMessage objectMessage=(ObjectMessage) message;
			try {
				smsService.sendSMS((SMSBean)objectMessage.getObject());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}
