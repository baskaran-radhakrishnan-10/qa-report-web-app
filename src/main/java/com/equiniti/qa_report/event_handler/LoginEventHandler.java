package com.equiniti.qa_report.event_handler;

import org.apache.log4j.Logger;

import com.equiniti.qa_report.dao.api.LoginDAO;
import com.equiniti.qa_report.event.login.DoLoginEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.IEvent;
import com.equiniti.qa_report.eventapi.eventhandling.handler.IEventHandler;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.security.crypto.EncryptionDecryption;

public class LoginEventHandler implements IEventHandler<IEvent> {

    private static final Logger LOG = Logger.getLogger(LoginEventHandler.class.getName());
    
    private EncryptionDecryption cryptoService;

    private LoginDAO loginDAO;

	public void setCryptoService(EncryptionDecryption cryptoService) {
		this.cryptoService = cryptoService;
	}

	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	public void processEvent(IEvent event) throws EventException {
        LOG.debug("processEvent START");
        if (event instanceof DoLoginEvent) {
            LOG.debug("Event :" + DoLoginEvent.class.getName());
            DoLoginEvent eventObj = (DoLoginEvent) event;
            doLogin(eventObj);
        }
        LOG.debug("processEvent END");
    }

    private void doLogin(DoLoginEvent event) throws EventException {
        try {
        	event.setUser(loginDAO.getUserByUserIdAndPassword(event.getUserId(), cryptoService.encrypt(event.getPassword())));
        } catch (DaoException e) {
            LOG.error("DaoException Occured", e);
            throw new EventException(e.getFaultCode(), e);
        } catch (Exception e) {
            LOG.error("Unknown Exception Occured", e);
            throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
        }
    }
    
}
