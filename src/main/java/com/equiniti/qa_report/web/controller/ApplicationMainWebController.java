package com.equiniti.qa_report.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.jcs.access.exception.CacheException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.equiniti.qa_report.controller.LoginController;
import com.equiniti.qa_report.controller.SMSController;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.exception.UIException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.form.model.LoginModelAttribute;
import com.equiniti.qa_report.util.ApplicationConstants;


@Controller
public class ApplicationMainWebController {
	
	private static final Logger LOG=Logger.getLogger(ApplicationMainWebController.class);
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private SMSController smsController;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private LoginController loginController;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String rootUrl(HttpServletRequest request,HttpServletResponse response){
		LOG.info("/super");
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/home")
	public String showHomePage(){
		return "homepage";
	}
	
	@RequestMapping(value = "/dashboard")
	public String showDashBoard(){
		return "homepage";
	}
	
	@RequestMapping(value = "/login")
	public String showLoginPage(){
		return "loginpage";
	}
	
	@RequestMapping(value="/doLogin", method=RequestMethod.POST)
	public String doLoginPost(@ModelAttribute("loginModelAttribute")@Valid LoginModelAttribute loginModelAttribute,BindingResult result ,Model model) {
		if (result.hasErrors()) {
			return "loginpage";
		}
		loginModelAttribute.setModel(model);
		String resultPage=loginController.doLogin(loginModelAttribute);
		//smsController.sendOneTimePassword("919962932629", "919962932629", "598745632145656");
		model=loginModelAttribute.getModel();
		return resultPage;
	}
	
	@RequestMapping(value = "/logout")
	public String doLogout() throws CacheException{
		loginController.doLogout();
		return ApplicationConstants.REDIRECT_LOGIN_PAGE;
	}
	
	@RequestMapping(value="/getSessionAttribute", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getSessionAttribute(@RequestBody Map<String,String> paramMap) throws UIException{
		LOG.info("Begin: ApplicationMainWebController.getSessionAttribute");
		Map<String,Object> resultData = new HashMap<>();
		Object sessionData = null;
		try {
			sessionData=session.getAttribute(paramMap.get("ATTRIBUTE_KEY"));
			resultData.put(paramMap.get("ATTRIBUTE_KEY"), sessionData);
		} catch (Exception e) {
			throw new UIException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.info("End: ApplicationMainWebController.getSessionAttribute");
		return resultData;
	}

}
