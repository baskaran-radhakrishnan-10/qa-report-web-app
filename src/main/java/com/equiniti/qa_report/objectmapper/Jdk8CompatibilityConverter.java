package com.equiniti.qa_report.objectmapper;

import java.time.LocalDate;

import org.dozer.CustomConverter;

public class Jdk8CompatibilityConverter implements CustomConverter {

	private CreatorFactory creatorFactory = new CreatorFactory();

	@Override
	public Object convert(Object destination, Object source, Class<?> destinationClass, Class<?> sourceClass) {

		if (destinationClass == null || sourceClass == null) {
			return destination;
		}

		if (source == null) {
			destination = null;
		} else if (destinationClass.isAssignableFrom(LocalDate.class) && sourceClass.isAssignableFrom(LocalDate.class)) {
			destination = creatorFactory.createLocalDateCreator().create(source);
		} else if(sourceClass.isAssignableFrom(java.util.Date.class) && destinationClass.isAssignableFrom(org.joda.time.DateTime.class)){
			destination = creatorFactory.createDateTimeCreator().create(source);
		}else if(sourceClass.isAssignableFrom(org.joda.time.DateTime.class) && destinationClass.isAssignableFrom(java.util.Date.class)){
			destination = creatorFactory.createDateCreator().create(source);
		}

		return destination;
	}
}
