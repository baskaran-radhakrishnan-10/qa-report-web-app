package com.equiniti.qa_report.objectmapper;

import java.util.Date;

import org.joda.time.DateTime;

public class DateTimeCreator implements Creator<DateTime> {

	@Override
	public DateTime create(Object source) {
		DateTime dateTime=null;
		if(source instanceof Date){
			Date date=(Date) source;
			dateTime=new DateTime(date);
		}
		return dateTime;
	}

}
