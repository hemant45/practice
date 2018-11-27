package com.example.practice;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CalendarUtil {
	Date date = new Date();

	private Calendar calender = Calendar.getInstance();
	
	public Map<String, Integer> getPresentTime() {
	calender.setTime(date);
	Map<String, Integer> paramMap = new HashMap<String, Integer>();
	paramMap.put("DATE.NOW.YYYY", calender.get(Calendar.YEAR));
	paramMap.put("DATE.NOW.MM", calender.get(Calendar.MONTH) + 1);
	paramMap.put("DATE.NOW.DD", calender.get(Calendar.DAY_OF_MONTH));
	
	paramMap.put("TIME.NOW.HH", calender.get(Calendar.HOUR));
	paramMap.put("TIME.NOW.MM", calender.get(Calendar.MINUTE));
	paramMap.put("TIME.NOW.SS", calender.get(Calendar.SECOND));
	paramMap.put("TIME.NOW.SSS", calender.get(Calendar.MILLISECOND));
	
	return paramMap;
	}
}
