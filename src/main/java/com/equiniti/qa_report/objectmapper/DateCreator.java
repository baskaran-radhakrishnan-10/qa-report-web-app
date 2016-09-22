package com.equiniti.qa_report.objectmapper;

import java.util.Date;

import org.joda.time.DateTime;

public class DateCreator implements Creator<Date> {

	@Override
	public Date create(Object source) {
		Date date=null;
		if(source instanceof DateTime){
			DateTime dateTime=(DateTime) source;
			date = dateTime.toDate();
		}
		return date;
	}

}
