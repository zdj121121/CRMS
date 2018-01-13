package com.cqupt.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateForMater {

	private static String defaultDatePattern = "yyyy-MM-dd";
	
	public  Date getforMaterDate(Date date){
		Date date2=null;
		SimpleDateFormat dateFormat=new SimpleDateFormat(defaultDatePattern);
		String dateString=dateFormat.format(date);
		try {
			date2=new SimpleDateFormat("yyyy-MM-dd ").parse(dateString);
			
			System.out.println(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return date2;
		
	}

	public static String getforMaterDateString(Date date){
		Date date2=null;
		SimpleDateFormat dateFormat=new SimpleDateFormat(defaultDatePattern);
		String dateString=dateFormat.format(date);
		
		
		return dateString;
		
	}
	
	
	
	public  String getDefaultDatePattern() {
		return defaultDatePattern;
	}

	public void setDefaultDatePattern(String defaultDatePattern) {
		this.defaultDatePattern = defaultDatePattern;
	}

	
}
