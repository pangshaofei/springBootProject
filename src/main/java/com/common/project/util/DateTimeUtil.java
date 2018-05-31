
package com.common.project.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class DateTimeUtil {

	private static final String YEAR_FORMAT = "yyyy";
	private static final String MONTH_FORMAT = "yyyy-MM";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm";
	private static final String DATESEC_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	private static final int HOUR_OF_DAY_MAX = 23;
	private static final int SECOND_MAX = 59;
	private static final int MINUTE_MAX = 59;
	private static final int MILLISECOND_MAX = 999;
	
	public static String formatYear(Date date) {
		return format(date, YEAR_FORMAT);
	}
	
	public static String formatMonth(Date date) {
		return format(date, MONTH_FORMAT);
	}
	
	public static String formatDate(Date date) {
		return format(date, DATE_FORMAT);
	}
	
	public static String formatMinute(Date time) {
		return format(time, DATETIME_FORMAT);
	}
	
	public static String formatSecond(Date time) {
		return format(time, DATESEC_FORMAT);
	}
	
	public static String format(Date time, String pattern){
		if(time == null)
			return null;
		return new SimpleDateFormat(pattern).format(time);
	}
	
	public static Date parseYear(String dateStr){
		return parse(dateStr, YEAR_FORMAT);
	}
	
	public static Date parseMonth(String dateStr){
		return parse(dateStr, MONTH_FORMAT);
	}
	
	public static Date parseDate(String dateStr)  {
		return parse(dateStr, DATE_FORMAT);
	}
	
	public static Date parseMinute(String timeStr)  {
		return parse(timeStr, DATETIME_FORMAT);
	}
	
	public static Date parseSecond(String timeStr)  {
		return parse(timeStr, DATESEC_FORMAT);
	}
	
	public static Date parse(String timeStr, String pattern){
		if(StringUtils.isBlank(timeStr) || StringUtils.isBlank(pattern))
			return null;
		try {
			return new SimpleDateFormat(pattern).parse(timeStr);
		} catch (ParseException e) {
			return null;
		}
	}
	
	
	/**
	 * 在某个日期的基础上增加num的天数，num可以为负数
	 * 
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date addDay(Date date, int num) {
		if (date == null) return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, num);
		return c.getTime();
	}
	
	/**
	 * 在某个时间的基础上增加num的分钟数，num可以为负数
	 * 
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date addMinute(Date date, int num) {
		if (date == null) return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, num);
		return c.getTime();
	}
	
	public static Date addSecond(Date date, int num) {
		if (date == null) return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, num);
		return c.getTime();
	}
	
	/**
	 * 在某个日期的基础上增加num的月数，num可以为负数
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date addMonth(Date date, int num) {
		if (date == null) return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, num);
		return c.getTime();
	}
	
	public static Date getDayStart(Date date) {
		if (date == null) return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	public static Date getDayEnd(Date date) {
		if (date == null) return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, HOUR_OF_DAY_MAX);
		c.set(Calendar.SECOND, SECOND_MAX);
		c.set(Calendar.MINUTE, MINUTE_MAX);
		c.set(Calendar.MILLISECOND, MILLISECOND_MAX);
		return c.getTime();
	}

	public static boolean before(Date date1, Date date2) {
		if(date1 == null)
			return date2 != null;
		if(date2 == null)
			return false;
		return date1.before(date2);
	}

	public static boolean after(Date date1, Date date2){
		if(date2 == null)
			return date1 != null;
		if(date1 == null)
			return false;
		return date1.after(date2);
	}

	public static boolean between(Date startDate, Date compareDate, Date endDate){
		return !after(compareDate, endDate) && !before(compareDate, startDate);
	}

	public static boolean across(Date startDate1, Date endDate1, 
								 Date startDate2, Date endDate2){
		return between(startDate1, startDate2, endDate1) || 
				between(startDate1, endDate2, endDate1) ||
				between(startDate2, startDate1, endDate2) || 
				between(startDate2, endDate1, endDate2);
	}
	
	public static void main(String[] args) {
		
		Date startDate1 = parseDate("2016-04-01 19:00");
		Date endDate1 = parseDate("2016-04-01 19:00");

		Date startDate2 = parseDate("2016-10-02 19:00");
//		Date endDate2 = parseDate("2016-10-06 19:00");

		System.out.println(between(startDate1, endDate1, startDate2));

		System.out.println("--------||||--------|||--------");
		System.out.println(parseYear("2016"));
		System.out.println(formatYear(startDate1));

	}
}
