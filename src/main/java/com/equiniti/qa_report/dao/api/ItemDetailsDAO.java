package com.equiniti.qa_report.dao.api;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.ItemEntity;
import com.equiniti.qa_report.exception.api.exception.DaoException;

public interface ItemDetailsDAO {

	public List<ItemEntity> getItemDetailsList(Map<String, Object> restrictionMap) throws DaoException;
	
	public List<String> getUniqueItemDescription() throws DaoException;

	public int addItemDetails(ItemEntity entity) throws DaoException;

	public void updateItemDetails(ItemEntity entity) throws DaoException;

}
