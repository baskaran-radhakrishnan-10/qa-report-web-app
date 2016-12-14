package com.equiniti.qa_report.queue.listener;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.equiniti.qa_report.email.EmailBean;
import com.equiniti.qa_report.email.EmailHandler;
import com.equiniti.qa_report.util.ApplicationConstants;


public class EmailQueueListener implements MessageListener{
	
	@Autowired
	private EmailHandler emailService;

	@SuppressWarnings("unchecked")
	@Override
	public void onMessage(Message message) {
		if(null != message && message instanceof ObjectMessage){
			ObjectMessage objectMessage=(ObjectMessage) message;
			try {
				emailService.sendEmail((EmailBean)objectMessage.getObject());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		else if(null != message && message instanceof MapMessage){
			MapMessage mapMessage=(MapMessage) message;
			try {
				Map<String,Object> emailDetailsMap=(Map<String, Object>) mapMessage.getObject(ApplicationConstants.EMAIL_DETAILS_MAP);
				emailService.sendEmail(emailDetailsMap);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}
