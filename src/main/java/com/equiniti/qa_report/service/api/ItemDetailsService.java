package com.equiniti.qa_report.service.api;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.ItemEntity;
import com.equiniti.qa_report.exception.api.exception.APIException;

public interface ItemDetailsService {

	public List<ItemEntity> getItemDetailsByItem(Map<String, Object> inputParam) throws APIException;
	
	public List<String> getUniqueItemDescList() throws APIException;

	public int addItemDetails(Map<String, Object> inputParam) throws APIException;

	public boolean updateItemDeatils(Map<String, Object> inputParam) throws APIException;

}
