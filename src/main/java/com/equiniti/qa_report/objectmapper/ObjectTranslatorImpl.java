package com.equiniti.qa_report.objectmapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;

public class ObjectTranslatorImpl implements ObjectTranslatorAPI{

	private DozerBeanMapper beanMapper ;

	public void setBeanMapper(DozerBeanMapper beanMapper) {
		this.beanMapper = beanMapper;
	}

	public Object translateObject(Object source,Object destination,String mapperId){
		beanMapper.map(source, destination, mapperId);
		return destination;
	}

	public Object translateObjectByClass(Object source,Class<?> destinationClass,String mapperId){
		Object destObj=beanMapper.map(source, destinationClass, mapperId);
		return destObj;
	}

	public <T, U> List<U> listConverter(final List<T> source, final Class<U> destType,String mapperId) {

		final List<U> dest = new ArrayList<U>();

		for (T element : source) {
			if (null != element) {
				dest.add(beanMapper.map(element, destType,mapperId));
			}
		}
		// finally remove all null values if any
		List<?> s1 = new ArrayList<>();
		s1.add(null);
		dest.removeAll(s1);

		return dest;
	}

}
