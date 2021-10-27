package com.adminpanel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	
	public static SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	
	 public static String convertObjectToJson(Object object) throws JsonProcessingException {
	        if (object == null) {
	            return null;
	        }
	        ObjectMapper mapper = new ObjectMapper();
	        return mapper.writeValueAsString(object);
	    }
	 public static Date changeDays(Date date, int days)
	    {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.DATE, days); //minus number would decrement the days
	        return cal.getTime();
	    }
	 
	 public static String formatDateTime(Date date) {
		   SimpleDateFormat formatter = Utils.dateformat;
		    String strDate= formatter.format(date);  
		    return strDate;
	 }
	 
	 public static Date stringToDate(String dateString) {
		
		 
		 Date date = null;
		 
		 try {
			 date = dateformat.parse(dateString);
		 }catch(Exception ex){
			 date = new Date();
		 }
		 
		 return date;
	 }
}
