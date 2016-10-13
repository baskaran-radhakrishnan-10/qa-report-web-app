package com.equiniti.qa_report.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class DateAndTimeUtil {
	
	public static Date getCurrentDate(){
		Calendar calendar=Calendar.getInstance();
		return calendar.getTime();
	}
	
	public static SimpleDateFormat getDateFormator(String dateFormat){
		SimpleDateFormat formater=new SimpleDateFormat(dateFormat);
		return formater;
	}
	
	public static Date getFormattedCurrentDate(String dateFormat) throws ParseException{
		Date currentDate=getCurrentDate();
		SimpleDateFormat formater=new SimpleDateFormat(dateFormat);
		return formater.parse(formater.format(currentDate));
	}
	
	public static Date getDateFromString(String dateFormat,String dateValue) throws ParseException{
		SimpleDateFormat formater=getDateFormator(dateFormat);
		Date date=formater.parse(dateValue);
		return date;
	}
	
	public static String getFormattedDateString(String dateFormat) throws ParseException{
		Date currentDate=getCurrentDate();
		SimpleDateFormat formater=new SimpleDateFormat(dateFormat);
		return formater.format(currentDate);
	}
	
	public static String getFormattedDateString(Date inputDate,String dateFormat)  throws ParseException{
		SimpleDateFormat formater=new SimpleDateFormat(dateFormat);
		return formater.format(inputDate);
	}
	
	public static LocalDate getFormattedLocalCurrentDate(String dateFormat) throws ParseException{
		Date formattedDate=getFormattedCurrentDate(dateFormat);
		Instant instant = formattedDate.toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDate  date = zdt.toLocalDate();
		return date;
	}
	
	public static LocalDate getLocalDateFromTime(Long time){
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeInMillis(time);
		Instant instant = calendar.getTime().toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDate  date = zdt.toLocalDate();
		return date;
	}
	
	public static Date getDateByTime(Date date,int hour,int min,int sec,int milliSec){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, hour);  
		calendar.set(Calendar.MINUTE, min);  
		calendar.set(Calendar.SECOND, sec);  
		calendar.set(Calendar.MILLISECOND, milliSec);  
		return calendar.getTime();
	}
	
	public static Date getDateAfterOrBeforeNoOfDays(Date date,int period){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 30);
		return calendar.getTime();
	}
	
	public static DateTime getDateAfterOrBefore(DateTime dateTime,long miliSeconds){
		
		Date preDate=getDateFromDateTime(dateTime);
		
		Date postDate = new Date(preDate.getTime() + miliSeconds);
		
		return getDateTimeFromDate(postDate);
	}
	
	public static Date getDateFromDateTime(DateTime dateTime){
		return dateTime.toDate();
	}
	
	public static DateTime getDateTimeFromDate(Date date){
		return new DateTime(date);
	}
	
	public static boolean checkDateExpired(Date endDate){
		Date currentDate=getCurrentDate();
		if(currentDate.after(endDate)){
			return true;
		}
		return false;
	}
	
	public static boolean checkDateExpired(Date startDate,Date endDate){
		Date currentDate= null != startDate ? startDate : getCurrentDate();
		if(currentDate.after(endDate)){
			return true;
		}
		return false;
	}
	
	public static DateTime getDateTimeFromMilliseconds(long milliseconds){
		return new DateTime(milliseconds, DateTimeZone.UTC);
	}
	

}
