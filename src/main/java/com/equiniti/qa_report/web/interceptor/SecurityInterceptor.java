package com.equiniti.qa_report.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.equiniti.qa_report.form.model.LoginModelAttribute;
import com.equiniti.qa_report.util.ApplicationConstants;

public class SecurityInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger LOG= Logger.getLogger(SecurityInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String uri = request.getRequestURI();
		
		System.out.println("inside interceptor  and uri = "+uri);

		boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

		System.out.println("ajax :"+ajax);

		HttpSession session=request.getSession();
		
		if(null == session.getAttribute(ApplicationConstants.BASE_URL)){
			
			String baseUrl = String.format("%s://%s:%d%s/",request.getScheme(),request.getServerName(), request.getServerPort(),request.getContextPath());
			
			request.setAttribute(ApplicationConstants.BASE_URL, baseUrl);
			
		}

		if(null == session.getAttribute(ApplicationConstants.IS_LOGGED_IN)){
			
			request.setAttribute("loginModelAttribute", new LoginModelAttribute());
			
			if(ajax){
				session.setAttribute("REDIRECT_TO_LOGIN_PAGE", true);
				return true;
			}

			if(-1 != uri.indexOf("/doLogin")){
				if(request.getMethod().equalsIgnoreCase("GET")){
					request.setAttribute("loginModelAttribute", new LoginModelAttribute());
					response.sendRedirect(request.getContextPath()+"/login");
					return false;
				}
				return true;
			}
			
			if ((-1 == uri.indexOf("/login") ) && uri.indexOf(".js") == -1 && uri.indexOf(".css") == -1 && uri.indexOf(".png") == -1 && uri.indexOf(".gif") == -1 && uri.indexOf(".jpg") == -1  && uri.indexOf(".ttf") == -1) {
				System.out.println("<<<< -- There is no session Available -- >>>>");
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
		System.out.println("Post handle :");
	}

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)throws Exception {
		System.out.println("After Complettion :");
	}

}
