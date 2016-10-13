package com.equiniti.qa_report.queue.connector;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service("reportQueueConnector")
public class ReportQueueConnector {

	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	Queue reportQueue;

	public void produce(Map<String,Object> exportObject) {
		jmsTemplate.send(reportQueue,new MessageCreator() {
			
			public Message createMessage(Session session) throws JMSException {
				
				ObjectMessage objectMessage=session.createObjectMessage();
				
				objectMessage.setObjectProperty("REPORT_DATA", exportObject.get("REPORT_DATA"));
				objectMessage.setObjectProperty("REPORT_TYPE", exportObject.get("REPORT_TYPE"));
				objectMessage.setObjectProperty("USER_ID", exportObject.get("USER_ID"));
				
				return objectMessage;
				
			}
			
		});
	}
}
