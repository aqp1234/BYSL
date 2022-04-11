package com.kms.bysl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class TimestampUtil {
	
	public static Timestamp getDate(int year, int month, int date) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, date, 00, 00, 00);
		return new Timestamp(new Date(cal.getTimeInMillis()).getTime());
	}
	
	public static Timestamp getFirstDate(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1);
		return new Timestamp(new Date(cal.getTimeInMillis()).getTime());
	}
	
	public static Timestamp getLastDate(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1);
		cal.set(year, month - 1, cal.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
		return new Timestamp(new Date(cal.getTimeInMillis()).getTime());
	}
}
