package com.equiniti.qa_report.service.api;

import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.form.model.LoginModelAttribute;

public interface LoginService {
	
	public LoginModelAttribute doLogin(LoginModelAttribute modelAttribute) throws  APIException;

}
