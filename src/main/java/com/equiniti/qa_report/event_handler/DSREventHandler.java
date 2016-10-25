package com.equiniti.qa_report.event_handler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.apache.jcs.access.exception.CacheException;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.equiniti.qa_report.cache.CacheInstance;
import com.equiniti.qa_report.dao.api.DSRDAO;
import com.equiniti.qa_report.entity.DSREntity;
import com.equiniti.qa_report.event.dsr.AddDSREvent;
import com.equiniti.qa_report.event.dsr.GetDSREvent;
import com.equiniti.qa_report.event.dsr.UpdateDSREvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.IEvent;
import com.equiniti.qa_report.eventapi.eventhandling.handler.IEventHandler;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.util.ApplicationConstants;

public class DSREventHandler implements IEventHandler<IEvent> {

	private static final Logger LOG = Logger.getLogger(DSREventHandler.class.getName());

	private ObjectMapper objMapper=new ObjectMapper();

	private DSRDAO dsrDAO;

	private CacheInstance CACHE_INS;
	
	@Autowired
	private HttpSession session;
	
	private String userId = null;

	public void setDsrDAO(DSRDAO dsrDAO) {
		this.dsrDAO = dsrDAO;
	}

	public void processEvent(IEvent event) throws EventException {
		userId = (String) session.getAttribute(ApplicationConstants.USER_ID);
		LOG.debug("processEvent START");
		if (event instanceof AddDSREvent) {
			LOG.debug("Event :" + AddDSREvent.class.getName());
			AddDSREvent eventObj = (AddDSREvent) event;
			addDSR(eventObj);
		}else if (event instanceof UpdateDSREvent) {
			LOG.debug("Event :" + UpdateDSREvent.class.getName());
			UpdateDSREvent eventObj = (UpdateDSREvent) event;
			updateDSR(eventObj);
		}else if (event instanceof GetDSREvent) {
			LOG.debug("Event :" + GetDSREvent.class.getName());
			GetDSREvent eventObj = (GetDSREvent) event;
			getDSR(eventObj);
		}else {
			LOG.debug("Unknow Event");
		}
		LOG.debug("processEvent END");
	}

	private void addDSR(AddDSREvent event) throws EventException {
		try {
			DSREntity entity = populateEntityFromMap(event.getParamMap());
			event.setAddedRow(dsrDAO.addDSREntity(entity));
			entity.setsNo(event.getAddedRow());
			addDataInCache(entity.getsNo(), entity);
		} catch (DaoException e) {
			throw new EventException(e.getFaultCode(), e);
		} catch (Exception e) {
			throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}

	private void updateDSR(UpdateDSREvent event) throws EventException {
		try {
			DSREntity entity = populateEntityFromMap(event.getParamMap());
			dsrDAO.updateDSREntity(entity);
			updateDataInCache(entity.getsNo(), entity);
		} catch (DaoException e) {
			event.setUpdated(false);
			throw new EventException(e.getFaultCode(), e);
		} catch (Exception e) {
			event.setUpdated(false);
			throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}

	@SuppressWarnings("unchecked")
	private void getDSR(GetDSREvent event) throws EventException {
		try {
			if (event.isListAll() || (null != event.getRestrictionMap())) {
				CACHE_INS=CacheInstance.getInstance();
				if(null != CACHE_INS.getItemFromCache(ApplicationConstants.DSR_CACHE_ITEM,userId)){
					List<DSREntity> dsrEntityList=(List<DSREntity>) CACHE_INS.getItemFromCache(ApplicationConstants.DSR_CACHE_ITEM,userId);
					event.setDSREntityList(dsrEntityList.subList(0, 999));
				}else{
					event.setDSREntityList(dsrDAO.getDSREntityList(event.getRestrictionMap()));
					CACHE_INS.putItemInCache(ApplicationConstants.DSR_CACHE_ITEM, event.getDSREntityList(),userId);
					designDSRCacheData();
				}
			} else if (null != event.getRestrictionMap()) {
				event.setDSREntityList(dsrDAO.getDSREntityList(event.getRestrictionMap()));
			} 
		} catch (DaoException e) {
			throw new EventException(e.getFaultCode(), e);
		} catch (Exception e) {
			throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}

	private DSREntity populateEntityFromMap(Map<String,Object> mapObject){
		DSREntity entity=objMapper.convertValue(mapObject, DSREntity.class);
		return entity;
	}

	private void designDSRCacheData() throws CacheException{
		new Thread(new Runnable() {
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				Long t1=0L,t2=0L;
				t1=System.nanoTime();
				Map<Integer,DSREntity> dsrIndexMap=new TreeMap<>();
				Map<Integer,Map<Integer,DSREntity>> paginationDataMap=new LinkedHashMap<>();
				List<DSREntity> dsrEntityList=(List<DSREntity>) CACHE_INS.getItemFromCache(ApplicationConstants.DSR_CACHE_ITEM,userId);
				int pageNo=1;
				for(int index = 0 ; index < dsrEntityList.size(); index++){
					DSREntity entity = dsrEntityList.get(index);
					dsrIndexMap.put(entity.getsNo(), entity);
					if((index % 999) == 0){
						if(index > 0){
							paginationDataMap.put(pageNo, dsrIndexMap);
							dsrIndexMap = new TreeMap<>();
							pageNo++;
						}
					}
					if(index == (dsrEntityList.size() - 1)){
						paginationDataMap.put(pageNo, dsrIndexMap);
						dsrIndexMap = new TreeMap<>();
					}
				}
				t2=System.nanoTime();
				System.out.println("Total time taken :"+(t2-t1));
				try {
					CACHE_INS.putItemInCache(ApplicationConstants.PAGED_DSR_CACHE_ITEM, paginationDataMap,userId);
					CACHE_INS.putItemInCache(ApplicationConstants.DSR_RECORDS_COUNT, dsrEntityList.size(),userId);
				} catch (CacheException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	@SuppressWarnings("unchecked")
	private void addDataInCache(int sNo,DSREntity entity) throws ControllerException{
		try {
			CacheInstance CACHE_INS=CacheInstance.getInstance();
			Map<Integer,Map<Integer,DSREntity>> pagedDsrEntity=(Map<Integer, Map<Integer, DSREntity>>) CACHE_INS.getItemFromCache(ApplicationConstants.PAGED_DSR_CACHE_ITEM,userId);
			if(null != pagedDsrEntity){
				Set<Integer> keySet = pagedDsrEntity.keySet();
				int lastPageIndex=keySet.size();
				Map<Integer,DSREntity> dsrIndexedMap=pagedDsrEntity.get(lastPageIndex);
				if(dsrIndexedMap.size() < 1000){
					dsrIndexedMap.put(sNo, entity);
				}else{
					dsrIndexedMap=new TreeMap<>();
					dsrIndexedMap.put(sNo, entity);
					pagedDsrEntity.put(lastPageIndex+1, dsrIndexedMap);
				}
				CACHE_INS.putItemInCache(ApplicationConstants.DSR_RECORDS_COUNT, ((Integer)CACHE_INS.getItemFromCache(ApplicationConstants.DSR_RECORDS_COUNT))+1 , userId);
			}
		} catch (Exception e) {
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void updateDataInCache(int sNo,DSREntity entity) throws ControllerException{
		try {
			CacheInstance CACHE_INS=CacheInstance.getInstance();
			Map<Integer,Map<Integer,DSREntity>> pagedDsrEntity=(Map<Integer, Map<Integer, DSREntity>>) CACHE_INS.getItemFromCache(ApplicationConstants.PAGED_DSR_CACHE_ITEM,userId);
			if(null != pagedDsrEntity){
				for(int pageNo : pagedDsrEntity.keySet()){
					Map<Integer,DSREntity> dsrIndexedMap=pagedDsrEntity.get(pageNo);
					if(dsrIndexedMap.containsKey(sNo)){
						dsrIndexedMap.put(sNo, entity);
						break;
					}
				}
			}
		} catch (Exception e) {
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}

}
