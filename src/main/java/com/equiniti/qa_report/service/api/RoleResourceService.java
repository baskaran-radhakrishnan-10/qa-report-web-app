package com.equiniti.qa_report.service.api;

import java.util.List;

import com.equiniti.qa_report.entity.RolesResources;


public interface RoleResourceService {
	
	public void addRolesResources(RolesResources rolesResources);

    public void removeRolesResources(int gKey);

    public void updateRolesResources(RolesResources rolesResources);

    public List<RolesResources> getRolesResourcesByRoleId(int roleId);
    
    public List<RolesResources> listRolesResources();

}
