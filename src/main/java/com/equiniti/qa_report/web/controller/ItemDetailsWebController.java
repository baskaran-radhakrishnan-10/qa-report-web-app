package com.equiniti.qa_report.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.equiniti.qa_report.controller.ItemDetailsController;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.exception.UIException;

@Controller
@RequestMapping(value="/item_details")
public class ItemDetailsWebController {
	
	@Autowired
	private ItemDetailsController itemDetailsController;
	
	@RequestMapping(value="/getItemDetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getItemDeatilsByBtoNo(@RequestBody Map<String,Object> inputParam){
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=itemDetailsController.getItemDeatilsByBtoNo(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		return returnObj;
	}
	
	@RequestMapping(value="/getUniqueItemDesc", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getUniqueResourceDetails(Map<String,Object> inputParam){
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=itemDetailsController.getUniqueItemDescList(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		return returnObj;
	}
	
	@RequestMapping(value="/addItemDetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addItemDetails(@RequestBody Map<String,Object> inputParam){
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=itemDetailsController.addItemDetails(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		return returnObj;
	}
	
	@RequestMapping(value="/updateItemDetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateItemDeatils(@RequestBody Map<String,Object> inputParam){
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=itemDetailsController.updateItemDeatils(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		return returnObj;
	}
	
	@RequestMapping(value = "/deleteData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteData(@RequestBody Map<String,Object> inputData) throws UIException{
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=itemDetailsController.deleteItemDeatils(inputData);
		} catch (ControllerException e) {
			throw new UIException(e.getFaultCode(), e);
		}
		return returnObj;
	}

}
