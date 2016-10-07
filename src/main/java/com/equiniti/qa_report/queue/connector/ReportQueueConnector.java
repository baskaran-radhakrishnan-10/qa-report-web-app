package com.equiniti.qa_report.queue.connector;

import java.util.List;
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

	public void produce(List<Map<String,Object>> inputObj) {
		jmsTemplate.send(reportQueue,new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage objectMessage=session.createObjectMessage();
				objectMessage.setObjectProperty("OBJ", inputObj);
				return objectMessage;
			}
		});
	}
}
