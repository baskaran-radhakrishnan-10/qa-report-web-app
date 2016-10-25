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
					cache=JCS.getInstance("QAReportAppCache");
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
	
	public void removeAllItemFromGroup(String groupName) throws CacheException{
		for(Object key : cache.getGroupKeys(groupName)){
			removeItemFromCache(key, groupName);
		}
	}
	
	public void putItemInCache(Object key,Object value,String groupName) throws CacheException{
		cache.putInGroup(key, groupName, value);
	}
	
	public Object getItemFromCache(Object key,String groupName){
		return cache.getFromGroup(key, groupName);
	}
	
	public void removeItemFromCache(Object key,String groupName) throws CacheException{
		cache.remove(key, groupName);
		
	}

}
