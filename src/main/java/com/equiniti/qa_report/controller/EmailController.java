package com.equiniti.qa_report.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equiniti.qa_report.email.EmailBean;
import com.equiniti.qa_report.queue.connector.EmailQueueConnector;
import com.equiniti.qa_report.util.ApplicationConstants;


@Service("emailController")
public class EmailController {

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private EmailQueueConnector emailQueueConnector;

	public void sendClientLoginCredentials(String clientEmailId,String userId,String accountId,String password,String url){
		String bodyContent=constructClientLoginCredentialsNotificationTemplate(userId,accountId,password,url);
		String subject="Your Login Account Credentials";
		String[] emailTo={clientEmailId}; 
		sendEmail(emailTo, subject, bodyContent, null, null, null, null, null);
	}

	private String constructClientLoginCredentialsNotificationTemplate(String userId,String accountId,String password,String url){
		String templateContent=getTemplateContent(ApplicationConstants.LOGIN_CREDENTIALS_TEMPLATE_PATH);
		templateContent=templateContent.replace("{{ACCOUNT_ID}}", accountId);
		templateContent=templateContent.replace("{{USER_ID}}", userId);
		templateContent=templateContent.replace("{{PASSWORD}}", password);
		templateContent=templateContent.replace("{{URL}}", url);
		return templateContent;
	}

	private void sendEmail(String[] emailTo,String subject,String bodyContent,String[] ccTo,String[] bccTo,byte[] attchment,String attchmentName,String attchmentType){
		EmailBean bean=new EmailBean();
		if(null != emailTo){
			bean.setEmailTo(emailTo);
		}
		if(null != subject){
			bean.setSubject(subject);
		}
		if(null != bodyContent){
			bean.setBodyContent(bodyContent);
		}
		if(null != ccTo){
			bean.setCcTo(ccTo);
		}
		if(null != bccTo){
			bean.setBccTo(bccTo);
		}
		if(null != attchment){
			bean.setAttachmentContent(attchment);
		}
		if(null != attchmentName){
			bean.setAttachmentFileName(attchmentName);
		}
		if(null != attchmentType){
			bean.setAttachmentType(attchmentType);
		}
		emailQueueConnector.produce(bean);
	}

	private String getTemplateContent(String staticTemplatePath){
		InputStream inputStream = null;
		String sCurrentLine=null;
		StringBuffer buffer=new StringBuffer();
		try {
			inputStream = servletContext.getResourceAsStream(staticTemplatePath);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			try {
				while ((sCurrentLine = bufferedReader.readLine()) != null) {
					buffer.append(sCurrentLine);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return buffer.toString();
	}

}
