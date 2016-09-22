/**
 * 
 */
package com.equiniti.qa_report.dao.api;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.RolesResources;
import com.equiniti.qa_report.exception.api.exception.DaoException;


/**
 * @author 
 *
 */
public interface RoleResourceDAO {

	public void addRolesResources(RolesResources rolesResources) throws DaoException;

    public void removeRolesResources(Map<String, Object> restrictionMap) throws DaoException;

    public void updateRolesResources(RolesResources rolesResources) throws DaoException;

    public List<RolesResources> getRolesResources(Map<String, Object> restrictionMap) throws DaoException;
    
    public List<RolesResources> listRolesResources(Map<String, Object> restrictionMap) throws DaoException;
}
