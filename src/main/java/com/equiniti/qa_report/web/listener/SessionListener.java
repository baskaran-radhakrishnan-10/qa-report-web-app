package com.equiniti.qa_report.web.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class SessionListener implements HttpSessionListener {

	private static final Logger LOG = Logger.getLogger(SessionListener.class);

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent sessionEvt) {
		LOG.debug("entered into sessionCreated()");
		System.out.println("entered into sessionCreated()");
		HttpSession session =  sessionEvt.getSession();
		try{
			System.out.println("Session Id :"+session.getId());
			System.out.println("session.getAttribute(REDIRECT_TO_LOGIN_PAGE) : "+session.getAttribute("REDIRECT_TO_LOGIN_PAGE"));
			sessionEvt.getSession().setMaxInactiveInterval(50*60);
		}catch(Exception e){
			LOG.warn("SESSION_TIMEOUT_DURATION is not configured properly in core.properties. Please check.");
		}
		System.out.println("end of sessionCreated()");
		LOG.debug("end of sessionCreated()");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent sessionEvt) {
		LOG.debug("SESSIONDESTROYED Method called...");
		System.out.println("SESSIONDESTROYED Method called...");
		CacheManager cacheManager=CacheManager.getInstance();
		HttpSession session =  sessionEvt.getSession();
		if(null != cacheManager.getCache(session.getId())){
			cacheManager.getCache(session.getId()).put(new Element("SESSION_ID", null));
			cacheManager.getCache(session.getId()).put(new Element("SESSION_ALIVE", false));
		}
		LOG.debug("Called session destroy for user" );
		System.out.println("Called session destroy for user" );
		LOG.debug("Session is destoryed");
	}

}