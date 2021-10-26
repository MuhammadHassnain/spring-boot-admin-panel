package com.adminpanel.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
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
		   SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");  
		    String strDate= formatter.format(date);  
		    return strDate;
	 }
}
