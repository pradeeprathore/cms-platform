package com.ascent.cms.core.util;

import java.util.Date;

import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

public class AgeCalculation {
	
	public static String getAgeAsString(Date dateOfBirth){
		
		LocalDate dob=new LocalDate(dateOfBirth);
		LocalDate now = new LocalDate();
		Period period = new Period(dob, now, PeriodType.yearMonthDay());
		
		int year=period.getYears();
		int month=period.getMonths();
		int day=period.getDays();
		
		if(year>0){
			return  year>1?year+" Years":year+" Year";
		}else if(year== 0 && month>0){
			return  month>1?month+" Months":month+" Month";
		}else if(year== 0 && month==0 && day>0){
			return  day>1?day+" Days":day+" day";
		}else{
			return "";
		}
		
	}

}
