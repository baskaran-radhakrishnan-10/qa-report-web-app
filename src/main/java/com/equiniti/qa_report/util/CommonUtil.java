package com.equiniti.qa_report.util;

import java.util.Map;
import java.util.Set;

public class CommonUtil {
	
	@SuppressWarnings("unchecked")
	public static Map<String,Object> removeTransientObject(Map<String,Object> mapObject){
		if(null != mapObject && !mapObject.isEmpty()){
			if(mapObject.containsKey(ApplicationConstants.TRANSIENT_ID)){
	    		mapObject.remove(ApplicationConstants.TRANSIENT_ID);
	    	}
	    	if(mapObject.containsKey(ApplicationConstants.TRANSIENT_LOG_DETAIL)){
	    		mapObject.remove(ApplicationConstants.TRANSIENT_LOG_DETAIL);
	    	}
		}
		Set<String> keySet = mapObject.keySet();
		for(String key : keySet){
			Object obj = mapObject.get(key);
			if(obj instanceof Map<?,?>){
				Map<String,Object> innerObj = (Map<String, Object>) mapObject.get(key);
				removeTransientObject(innerObj);
			}
		}
    	return mapObject;
	}
	
}
