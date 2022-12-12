package com.yf.exam.core.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期处理工具类
 * ClassName: DateUtils <br/>
 * date: 2018年12月13日 下午6:34:02 <br/>
 *
 * @author Bool
 * @version
 */
public class DateUtils {
	
	/**
	 * 
	 * calcExpDays:计算某个日期与当前日期相差的天数，如果计算的日期大于现在时间，将返回负数；否则返回正数 <br/>
	 * @author Bool
	 * @param userCreateTime
	 * @return
	 * @since JDK 1.6
	 */
	public static int calcExpDays(Date userCreateTime){
		
		Calendar start = Calendar.getInstance();
		start.setTime(userCreateTime);

		Calendar now = Calendar.getInstance();
		now.setTime(new Date());

		long l = now.getTimeInMillis() - start.getTimeInMillis();
		int days = new Long(l / (1000 * 60 * 60 * 24)).intValue();
		return days;
	}


	/**
	 * 
	 * dateNow:获取当前时间的字符串格式，根据传入的格式化来展示. <br/>
	 * @author Bool
	 * @param format 日期格式化
	 * @return
	 */
	public static String dateNow(String format) {
		SimpleDateFormat fmt = new SimpleDateFormat(format);
		Calendar c = new GregorianCalendar();
		return fmt.format(c.getTime());
	}
	
	/**
	 * formatDate:格式化日期，返回指定的格式 <br/>
	 * @author Bool
	 * @param time
	 * @param format
	 * @return
	 */
	public static String formatDate(Date time, String format) {
		SimpleDateFormat fmt = new SimpleDateFormat(format);
		return fmt.format(time.getTime());
	}

	

	/**
	 * parseDate:将字符串转换成日期，使用：yyyy-MM-dd HH:mm:ss 来格式化
	 * @author Bool
	 * @param date
	 * @return
	 */
	public static Date parseDate(String date) {
		return parseDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	
	/**
	 * 
	 * parseDate:将字符串转换成日期，使用指定格式化来格式化
	 * @author Bool
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date parseDate(String date, String pattern) {

		if (pattern==null) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}

		SimpleDateFormat fmt = new SimpleDateFormat(pattern);

		try {

			return fmt.parse(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}
}
