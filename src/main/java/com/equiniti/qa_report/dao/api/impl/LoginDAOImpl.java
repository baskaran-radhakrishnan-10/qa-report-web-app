package com.equiniti.qa_report.dao.api.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.equiniti.qa_report.dao.api.LoginDAO;
import com.equiniti.qa_report.entity.User;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.faultcode.DaoFaultCodes;
import com.equiniti.qa_report.persistance_api.hibernate.api.AbstractHibernateDAOAPI;

public class LoginDAOImpl implements LoginDAO{
	
	private static final Logger LOG=Logger.getLogger(LoginDAOImpl.class);
	
	private AbstractHibernateDAOAPI<User> abstractHibernateDAOAPI;
	
	public void setAbstractHibernateDAOAPI(AbstractHibernateDAOAPI<User> abstractHibernateDAOAPI) {
		this.abstractHibernateDAOAPI = abstractHibernateDAOAPI;
	}

	public User getUserByUserIdAndPassword(String userId,String password) throws DaoException{
		User user=null;
		Map<String,Object> restrictionMap=new HashMap<>();
		restrictionMap.put("user_id", userId);
		restrictionMap.put("password", password);
		restrictionMap.put("is_active", true);
		try {
			user=abstractHibernateDAOAPI.getEntity(User.class, restrictionMap);
		} catch (DaoException e) {
			throw new DaoException(DaoFaultCodes.HIBERNATE_GET_ENTITY_ERROR,e);
		}
		return user;
	}
	
}
