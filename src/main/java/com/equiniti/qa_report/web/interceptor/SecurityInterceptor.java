package com.equiniti.qa_report.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.equiniti.qa_report.exception.api.FaultCode;
import com.equiniti.qa_report.exception.api.exception.UIException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.form.model.LoginModelAttribute;
import com.equiniti.qa_report.util.ApplicationConstants;

public class SecurityInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger LOG= Logger.getLogger(SecurityInterceptor.class);
	
	private boolean ajax =false;
	
	private String uri=null;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		this.uri = request.getRequestURI();
		
		LOG.debug("inside interceptor  and uri = "+this.uri);

		this.ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

		LOG.debug("ajax :"+ajax);

		HttpSession session=request.getSession();
		
		if(null == session.getAttribute(ApplicationConstants.BASE_URL)){
			
			String baseUrl = String.format("%s://%s:%d%s/",request.getScheme(),request.getServerName(), request.getServerPort(),request.getContextPath());
			
			session.setAttribute(ApplicationConstants.BASE_URL, baseUrl);
			
		}

		if(null == session.getAttribute(ApplicationConstants.IS_LOGGED_IN)){
			
			request.setAttribute("loginModelAttribute", new LoginModelAttribute());
			
			if(ajax){
				session.setAttribute("REDIRECT_TO_LOGIN_PAGE", true);
				return false;
			}

			if(-1 != this.uri.indexOf("/doLogin")){
				if(request.getMethod().equalsIgnoreCase("GET")){
					request.setAttribute("loginModelAttribute", new LoginModelAttribute());
					response.sendRedirect(request.getContextPath()+"/login");
					return false;
				}
				return true;
			}
			
			if ((-1 == this.uri.indexOf("/login") ) && this.uri.indexOf(".js") == -1 && this.uri.indexOf(".css") == -1 && this.uri.indexOf(".png") == -1 && this.uri.indexOf(".gif") == -1 && this.uri.indexOf(".jpg") == -1  && this.uri.indexOf(".ttf") == -1) {
				LOG.debug("<<<< -- There is no session Available -- >>>>");
				request.setAttribute("loginModelAttribute", new LoginModelAttribute());
				response.sendRedirect(request.getContextPath()+"/login");
				return false;
			}else{
				return true;
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		LOG.debug("Post handle :");
	}

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)throws Exception {
		if(null != ex){
			if(ex instanceof UIException){
				LOG.debug("Exception Message :"+ex.getMessage());
				FaultCode faultCode = ((UIException) ex).getFaultCode();
				if(faultCode.equals(CommonFaultCode.CACHE_FAILED_ERROR)){
					//preHandle(request, response, handler);
				}
			}
		}
		LOG.debug("After Complettion :");
	}

}
