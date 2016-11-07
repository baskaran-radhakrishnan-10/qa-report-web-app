package com.equiniti.qa_report.dao.api;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.Remainder;
import com.equiniti.qa_report.exception.api.exception.DaoException;

public interface OperationDAO {
	
	public List<Remainder> getRemainderDetails (Map<String,Object> restrictionMap) throws DaoException;
	
	public int addRemainderDetails(Remainder entity) throws DaoException;
	
	public void updateRemainderDetails(Remainder entity) throws DaoException;
}
