package com.equiniti.qa_report.email;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.equiniti.qa_report.exception.api.exception.SecurityException;
import com.equiniti.qa_report.security.crypto.EncryptionDecryption;

public class EmailConfigService {
	
	private JavaMailSenderImpl senderImpl;
	
	public EmailConfigService(EncryptionDecryption cryptoService,JavaMailSenderImpl mailSenderImplObj,String password) throws SecurityException{
		this.senderImpl = mailSenderImplObj;
		this.senderImpl.setPassword(cryptoService.decrypt(password));
	}
	
	public JavaMailSenderImpl getMailSenderImplObj(){
		return this.senderImpl;
	}
	
}
