package com.equiniti.qa_report.dao.api;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.BtpEntity;
import com.equiniti.qa_report.exception.api.exception.DaoException;

public interface TestPlanDAO {
	
	public List<BtpEntity> getBtpEntityList(Map<String, Object> restrictionMap) throws DaoException;
	
	public int addBtpEntity(BtpEntity entity) throws DaoException;
	
	public void updateBtpEntity(BtpEntity entity) throws DaoException;
	
	public BtpEntity getBtpEntity(Map<String, Object> restrictionMap) throws DaoException;

	public List<String> getUniqueBtpYearList() throws DaoException;

	public void deleteBtpEntityByBTPNo(int btpNo) throws DaoException;

	public void deleteBtpEntityByBTPNoList(List<Integer> btpNoList) throws DaoException;

}
