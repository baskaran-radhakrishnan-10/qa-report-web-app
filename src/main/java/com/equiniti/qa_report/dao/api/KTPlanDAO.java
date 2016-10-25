package com.equiniti.qa_report.dao.api;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.KTPlan;
import com.equiniti.qa_report.exception.api.exception.DaoException;

public interface KTPlanDAO {
	
	public List<KTPlan> getKTPlanDetails(Map<String,Object> restrictionMap) throws DaoException;
	public int addKTDetails(KTPlan entity) throws DaoException;
	public void updateKTDetails(KTPlan entity) throws DaoException;

}
