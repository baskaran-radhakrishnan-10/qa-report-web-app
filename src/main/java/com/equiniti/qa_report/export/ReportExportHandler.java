package com.equiniti.qa_report.export;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class ReportExportHandler {
	
	public void exportDocument(List<Map<String,Object>> dataObj){
		
		System.out.println(dataObj);
		
		Map<Integer,Map<String,Object>> updateObjectMap=new HashMap<>();
		
		for(Map<String,Object> dataMap : dataObj){
			
			Map<String,Object> rowMap ;
			
			int totalResCount = 0 ;
			
			int estEffortCount = 0;
			
			int actualEffort = 0;
			
			int bugsLoggedCount = 0;
			
			int bugsAssinedCount = 0;
			
			int usTestedCount = 0;
			
			int btpNo = Integer.valueOf(String.valueOf(dataMap.get("btpno")));
			
			if(updateObjectMap.containsKey(btpNo)){
				rowMap=updateObjectMap.get(btpNo);
			}else{
				rowMap=new LinkedHashMap<>();
			}
			
			if(!rowMap.containsKey("Project Code")){
				rowMap.put("Project Code", dataMap.get("projectname"));
			}
			
			if(!rowMap.containsKey("Build Id")){
				rowMap.put("Build Id", dataMap.get("projectname") + "_" + dataMap.get("phase") + "_" + dataMap.get("cycle") + "_" + dataMap.get("buildno"));
			}
			
			if(!rowMap.containsKey("Start Date")){
				String startDate=((Timestamp)dataMap.get("startdate")).toString();
				rowMap.put("Start Date", startDate.substring(0, startDate.indexOf(" ")));
			}
			
			if(!rowMap.containsKey("End Date")){
				String endDate=((Timestamp)dataMap.get("enddate")).toString();
				rowMap.put("End Date", endDate.substring(0, endDate.indexOf(" ")));
			}
			
			Set<String> keySet = dataMap.keySet();
			
			for(String key : keySet){
				
				if(key.indexOf("resource") != -1){
					String resource = (String) dataMap.get(key);
					if(null != resource){
						totalResCount ++;
					}
				}else if(key.indexOf("bugslogged") != -1){
					
					bugsLoggedCount += (Integer)dataMap.get(key);
					
					rowMap.put("Bugs Logged",bugsLoggedCount);
					
				}else if(key.indexOf("acteffort") != -1){
					
					actualEffort += ((Double) dataMap.get(key)).intValue();
					
					rowMap.put("Actual (Hrs)",actualEffort);
					
				}else if(key.indexOf("esteffort") != -1){
					
					estEffortCount += ((Double) dataMap.get(key)).intValue();
					
					rowMap.put("Estd (Hrs)",estEffortCount);
					
				}else if(key.indexOf("itemcount") != -1){
					
					if(keySet.contains("itemdescription")){
						
						String itemDescription=(String)dataMap.get("itemdescription");
						
						if(itemDescription.indexOf("Bugs Verification") != -1){
							
							bugsAssinedCount += (Integer)dataMap.get(key);
							
							rowMap.put("Bugs Assigned",bugsAssinedCount);
							
						}else if(itemDescription.indexOf("US") != -1){
							
							usTestedCount += (Integer)dataMap.get(key);
							
							rowMap.put("US Tested",usTestedCount);
							
						}
					
					}
				}
				
			}
			
			if(!rowMap.containsKey("No of Resources")){
				rowMap.put("No of Resources",totalResCount);
			}
			
			if(!rowMap.containsKey("Revised End Date")){
				rowMap.put("Revised End Date", "");
				if(null != dataMap.get("revisedenddate")){
					String revEndDate=((Timestamp)dataMap.get("revisedenddate")).toString();
					rowMap.put("Revised End Date", revEndDate.substring(0, revEndDate.indexOf(" ")));
				}
			}
			
			if(!rowMap.containsKey("Test Status")){
				rowMap.put("Test Status", dataMap.get("btpstatus"));
			}
			
			if(!rowMap.containsKey("Remarks")){
				String remarks = (String) dataMap.get("btpremarks");
				rowMap.put("Remarks", remarks);
			}
			
			updateObjectMap.put(btpNo, rowMap);
			
		}
		
		System.out.println(updateObjectMap);
		
	}
	
}
