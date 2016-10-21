package com.equiniti.qa_report.queue.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.equiniti.qa_report.export.ReportExportHandler;
import com.equiniti.qa_report.util.ApplicationConstants;


public class ReportQueueListener implements MessageListener{
	
	@Autowired
	private ReportExportHandler reportExportHandler;

	@SuppressWarnings("unchecked")
	@Override
	public void onMessage(Message message) {
		if(null != message && message instanceof ObjectMessage){
			Map<String,Object> exportObject=new HashMap<>();
			ObjectMessage objectMessage=(ObjectMessage) message;
			try {
				long t1,t2;
				t1=System.nanoTime();
				List<Map<String,Object>> dataObj=(List<Map<String, Object>>) objectMessage.getObjectProperty("REPORT_DATA");
				String reportType=(String) objectMessage.getObjectProperty("REPORT_TYPE");
				String userId = (String) objectMessage.getObjectProperty("USER_ID");
				
				exportObject.put("REPORT_TYPE", reportType);
				exportObject.put("REPORT_DATA", dataObj);
				exportObject.put("USER_ID", userId);
				reportExportHandler.exportBTPReport(exportObject);
				
				if(ApplicationConstants.SELECTED_BTP_REPORT.intern() != reportType.intern()){
					exportObject.put("REPORT_TYPE", "BTP_WEEKLY_REPORT");
					reportExportHandler.exportBTPReport(exportObject);
				}
				
				t2=System.nanoTime();
				System.out.println("Total time taken to export XLS File :"+(t2-t1)+" in nano seconds");
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}
