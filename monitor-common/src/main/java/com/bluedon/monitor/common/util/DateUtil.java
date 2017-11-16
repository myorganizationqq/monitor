package com.bluedon.monitor.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;

/**
 * @author  : jason
 * @date    : 2015年3月4日
 * @module  : 公共模块 
 * @comment : 时间处理API
 */
public class DateUtil {
	
	
	private static final Logger log = Logger.getLogger(DateUtil.class);

	private static final String DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";

	/** yyyy-MM-dd HH:mm:ss */
	public static final String DATE_STYLE_TIME_1 = "yyyy-MM-dd HH:mm:ss";
	/** yyyyMMddHHmmss **/
	public static final String DATE_STYLE_TIME_2 = "yyyyMMddHHmmss";
	/** yyyy-MM-dd HH **/
	public static final String DATE_STYLE_TIME_3 = "yyyy-MM-dd HH";
	/** yyyy-MM-dd HH:mm **/
	public static final String DATE_STYLE_TIME_4 = "yyyy-MM-dd HH:mm";
	/** yyyyMMddHHmmssSSS **/
	public static final String DATE_STYLE_TIME_5 = "yyyyMMddHHmmssSSS";
	/** yyyyMMddHH **/
	public static final String DATE_STYLE_TIME_6 = "yyyyMMddHH";
	/** yyyyMMddHHmm **/
	public static final String DATE_STYLE_TIME_7 = "yyyyMMddHHmm";
	/** yyyyMMdd **/
	public static final String DATE_STYLE_TIME_8 = "yyyyMMdd";
	
	/** yyyy-MM-dd */
	public static final String DATE_STYLE_DATE_1 = "yyyy-MM-dd";
	/** yyyy-MM */
	public static final String DATE_STYLE_DATE_2 = "yyyy-MM";
	/** yyyy **/
	public static final String DATE_STYLE_DATE_3 = "yyyy";

	/**
	 * 获取当前时间的标准字符串 : yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getCurrDateString() {
		try {
			// return SDF_yMdHms.format(new Date());
			return DateFormatUtils.format(new Date(), DATE_FORMAT_YMDHMS);
		} catch (Exception e) {
			log.error("getCurrDateString Error:", e);
			return null;
		}
	}
	
	/**
	 * 获取当前时间的标准字符串 : 参数 DateUtil
	 * 
	 * @return
	 */
	public static String getCurrDateString(String dateFormat) {
		try {
			// return SDF_yMdHms.format(new Date());
			return DateFormatUtils.format(new Date(), dateFormat);
		} catch (Exception e) {
			log.error("getCurrDateString Error:", e);
			return null;
		}
	}

	/**
	 * 现有AMS系统中所有的时间字段都是使用String类型来表示,所以在比较的时候按照String类型的比较方法需要对某些时间进行增加<br/>
	 * 如:取2013-12-28到2013-12-30之间的数据的时候2013-12-30当天的数据会被剔除,因此需要对上限时间+1
	 * 
	 * @param dateStr
	 *            原始日期格式字符串
	 * @param vector
	 *            需要增加的天数
	 * @return 增加指定天数后的新的日期格式字符串
	 */
	public static String addDay(String dateStr, int vector) {
		String result = "";
		try {
			// Date tmpDate = SDF_yMd.parse(dateStr);
			String[] dateformat = { DATE_FORMAT_YMD };
			Date tmpDate = DateUtils.parseDate(dateStr, dateformat);
			Calendar tmpCal = Calendar.getInstance();
			tmpCal.setTime(tmpDate);
			tmpCal.add(Calendar.DATE, vector);
			// result = SDF_yMd.format(tmpCal.getTime()).substring(0, 10);
			result = DateFormatUtils.format(tmpCal.getTime(), DATE_FORMAT_YMD);
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("addDay Error:", e);
		}
		return result;
	}

	/**
	 * 判断是否传入参数str是否是有效的日期格式
	 * 
	 * @param str
	 *            需要判断的日期字符串
	 * @param pattern
	 *            判断字符串的格式
	 * @return true:是有效的日期字符串<br/>
	 *         false:不是有效的日期字符串
	 */
	public static boolean isDate(String dateStr, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			sdf.parse(dateStr);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 返回两个日期的分钟数差
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int minuteDiff(Date d1, Date d2) {
		if (d1 == null || d2 == null) {
			return 0;
		}
		Long diff = (d1.getTime() - d2.getTime()) / 1000 / 60;
		return diff.intValue();
	}

	/**
	 * 字串转化成日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date strToDate(String date, String pattern) {
		if (!StringUtil.isEmpty(date) || pattern == null) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 将Date格式的字符串转换为Calendar对象
	 * 
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static Calendar strToCalendar(String dateStr, String pattern) {
		Date dateResult = DateUtil.strToDate(dateStr, pattern);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateResult);
		return cal;
	}

	/**
	 * Date类型转换成String型日期
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date, String pattern) {
		if (date == null)
			return null;
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 将Calendar对象转换为指定格式的日期字符串
	 * 
	 * @param cal
	 * @param pattern
	 * @return
	 */
	public static String calendarToStr(Calendar cal, String pattern) {
		Date tmpDate = cal.getTime();
		return DateUtil.dateToString(tmpDate, pattern);
	}

	
}	
