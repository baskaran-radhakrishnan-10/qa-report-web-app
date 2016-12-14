package com.equiniti.qa_report.queue.connector;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.equiniti.qa_report.sms.SMSBean;


@Service("smsQueueConnector")
public class SMSQueueConnector {

	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	Queue smsQueue;

	public void produce(SMSBean bean) {
		jmsTemplate.send(smsQueue,new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage objectMessage=session.createObjectMessage(bean);
				return objectMessage;
			}
		});
	}
}
