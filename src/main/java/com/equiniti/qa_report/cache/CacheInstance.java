package com.equiniti.qa_report.cache;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;


public class CacheInstance {
	
	private static CacheInstance INSTANCE=null;
	
	private static JCS cache=null;
	
	private CacheInstance(){}
	
	public static CacheInstance getInstance() throws CacheException{
		if (INSTANCE == null) {
			synchronized (CacheInstance.class) {
				if (INSTANCE == null){
					INSTANCE = new CacheInstance();
				}
				if(null == cache){
					cache=JCS.getInstance("qa_report_app");
				}
			}
		}
		return INSTANCE;
	}
	
	public void putItemInCache(Object key,Object value) throws CacheException{
		cache.put(key, value);
	}
	
	public Object getItemFromCache(Object key){
		return cache.get(key);
	}
	
	public void removeItemFromCache(Object key) throws CacheException{
		cache.remove(key);
	}

}
