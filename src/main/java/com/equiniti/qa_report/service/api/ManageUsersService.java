package com.equiniti.qa_report.service.api;

import java.util.List;

import com.equiniti.qa_report.entity.User;
import com.equiniti.qa_report.exception.api.exception.APIException;

public interface ManageUsersService {

	public List<User> getUserDetails() throws APIException;
}
