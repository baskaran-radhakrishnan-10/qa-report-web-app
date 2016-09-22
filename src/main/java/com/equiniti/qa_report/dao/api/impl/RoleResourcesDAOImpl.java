package com.equiniti.qa_report.dao.api.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.equiniti.qa_report.dao.api.RoleResourceDAO;
import com.equiniti.qa_report.entity.RolesResources;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.persistance_api.hibernate.api.AbstractHibernateDAOAPI;


@Service("roleResourcesDAO")
public class RoleResourcesDAOImpl implements RoleResourceDAO {

	private AbstractHibernateDAOAPI<RolesResources> abstractHibernateDAOAPI;

	public AbstractHibernateDAOAPI<RolesResources> getAbstractHibernateDAOAPI() {
		return abstractHibernateDAOAPI;
	}

	public void setAbstractHibernateDAOAPI(AbstractHibernateDAOAPI<RolesResources> abstractHibernateDAOAPI) {
		this.abstractHibernateDAOAPI = abstractHibernateDAOAPI;
	}

	@Override
	public void addRolesResources(RolesResources rolesResources) throws DaoException {
		abstractHibernateDAOAPI.saveEntity(rolesResources);
	}

	@Override
	public void removeRolesResources(Map<String, Object> restrictionMap) throws DaoException {
		abstractHibernateDAOAPI.deleteEntity(RolesResources.class, restrictionMap);
	}

	@Override
	public void updateRolesResources(RolesResources rolesResources) throws DaoException {
		abstractHibernateDAOAPI.updateEntity(rolesResources);
	}

	@Override
	public List<RolesResources> getRolesResources(Map<String, Object> restrictionMap) throws DaoException {
		return abstractHibernateDAOAPI.getEntityList(RolesResources.class, restrictionMap);
	}

	@Override
	public List<RolesResources> listRolesResources(Map<String, Object> restrictionMap) throws DaoException {
		return abstractHibernateDAOAPI.getEntityList(RolesResources.class, null);
	}
}
