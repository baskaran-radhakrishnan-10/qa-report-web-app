package com.equiniti.qa_report.queue.listener;

import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.equiniti.qa_report.export.ReportExportHandler;


public class ReportQueueListener implements MessageListener{
	
	@Autowired
	private ReportExportHandler reportExportHandler;

	@SuppressWarnings("unchecked")
	@Override
	public void onMessage(Message message) {
		if(null != message && message instanceof ObjectMessage){
			ObjectMessage objectMessage=(ObjectMessage) message;
			try {
				List<Map<String,Object>> dataObj=(List<Map<String, Object>>) objectMessage.getObjectProperty("OBJ");
				reportExportHandler.exportDocument(dataObj);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}
