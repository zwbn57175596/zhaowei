package com.asiainfo.hya.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 时间处理函数
 * 
 * @author hxw
 */
public class DateUtil {

	private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 获取丄1�7个月的最后一处1�7
	 * @param month yyyyMM
	 * @return date yyyyMMdd
	 */
	public static String getLastDateOfMonth(String month) {
		if (null == month)
			return null;
		Date thisDate = str2Date(month, "yyyyMM");
		Calendar cal = Calendar.getInstance();
		cal.setTime(thisDate);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.roll(Calendar.DAY_OF_MONTH, -1);
		return date2Str(cal.getTime(), "yyyyMMdd");
	}

	/**
	 * 返回给定时间扄1�7在的月份，以数字的形弄1�7(201101＄1�71102＄1�71103.......)
	 * 
	 * @param date
	 *            传入时间 2011-02-28
	 * @return
	 */
	public static String getYearMonth(String date) {
		try {
			DateFormat format = DateFormat.getDateInstance();
			Date inputDate = format.parse(date);
			return date2Str(inputDate, "yyyyMM");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取相隔interval天的时间 并返回pattern类型的string
	 * 
	 * @param interval
	 * @param starttime
	 * @param pattern
	 * @return
	 */
	public static String getDate(String interval, Date starttime, String pattern) {
		Calendar temp = Calendar.getInstance(TimeZone.getDefault());
		temp.setTime(starttime);
		temp.add(temp.MONTH, Integer.parseInt(interval));
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(temp.getTime());
	}

	/**
	 * 将字符串类型转换为时间类垄1�7
	 * 
	 * @param str
	 * @return
	 */
	public static Date str2Date(String str) {
		Date d = null;
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
		try {
			d = sdf.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 将字符串按照pattern类型转换为时间类垄1�7
	 * 
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static Date str2Date(String str, String pattern) {
		Date d = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			d = sdf.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 将时间格式化
	 * 
	 * @return
	 */
	public static Date DatePattern(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
		try {
			String dd = sdf.format(date);
			date = str2Date(dd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 将时间格式化
	 */
	public static Date DatePattern(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			String dd = sdf.format(date);
			date = str2Date(dd, pattern);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 将date转换成默认时间格式的字符丄1�7
	 * 
	 * @param date
	 * @return
	 */
	public static String date2Str(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
		return sdf.format(date);
	}

	/**
	 * 将date转换成format格式的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String date2Str(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 获取昨天
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date getLastDate(Date date) {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.setTime(date);

		calendar.add(calendar.DATE, -1);

		return str2Date(date2Str(calendar.getTime()));
	}

	/**
	 * 获取上周第一天（周一＄1�7
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date getLastWeekStart(Date date) {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.setTime(date);
		int i = calendar.get(calendar.DAY_OF_WEEK) - 1;
		int startnum = 0;
		if (i == 0) {
			startnum = 7 + 6;
		} else {
			startnum = 7 + i - 1;
		}
		calendar.add(calendar.DATE, -startnum);

		return str2Date(date2Str(calendar.getTime()));
	}

	/**
	 * 返回指定月的朄1�7后一处1�7 比如 201002 获取2月的朄1�7后一处1�7
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getLastDayOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.YEAR, year);

		calendar.set(Calendar.MONTH, month);//

		calendar.set(Calendar.DATE, 1);

		calendar.add(Calendar.DATE, -1);

		int end = calendar.get(Calendar.DATE);

		// int begin=calendar.getActualMinimum(calendar.DAY_OF_MONTH);
		return end;
	}

	/**
	 * 获取上周朄1�7后一天（周末＄1�7
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date getLastWeekEnd(Date date) {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.setTime(date);
		int i = calendar.get(calendar.DAY_OF_WEEK) - 1;
		int endnum = 0;
		if (i == 0) {
			endnum = 7;
		} else {
			endnum = i;
		}
		calendar.add(calendar.DATE, -(endnum - 1));

		return str2Date(date2Str(calendar.getTime()));
	}

	/**
	 * 改更现在时间
	 */
	public static Date changeDate(String type, int value) {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		if (type.equals("month")) {
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + value);
		} else if (type.equals("date")) {
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + value);
		}
		return calendar.getTime();
	}

	/**
	 * 更改时间
	 */
	public static Date changeDate(Date date, String type, int value) {
		// Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		// Calendar calendar = Calendar.
		if (type.equals("month")) {
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + value);
		} else if (type.equals("date")) {
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + value);
		} else if (type.endsWith("year")) {
			calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + value);
		}
		return calendar.getTime();
	}

	/**
	 * 比较时间是否在这两个时间点之闄1�7
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean checkTime(String time1, String time2) {
		Calendar calendar = Calendar.getInstance();
		Date date1 = calendar.getTime();
		Date date11 = DateUtil.str2Date(DateUtil.date2Str(date1, "yyyy-MM-dd") + " " + time1);// 起始时间

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		Date date2 = c.getTime();
		Date date22 = DateUtil.str2Date(DateUtil.date2Str(date2, "yyyy-MM-dd") + " " + time2);// 终止时间

		Calendar scalendar = Calendar.getInstance();
		scalendar.setTime(date11);// 起始时间

		Calendar ecalendar = Calendar.getInstance();
		ecalendar.setTime(date22);// 终止时间

		Calendar calendarnow = Calendar.getInstance();

		if (calendarnow.after(scalendar) && calendarnow.before(ecalendar)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 棄1�7查输入日期是否是interval月之内的日期
	 * 
	 * @param time
	 * @param interval
	 * @return
	 */
	public static boolean checkOnly6Month(String time, int interval) {
		boolean t = true;
		Calendar calendarnow = Calendar.getInstance();
		Date datetmp = DateUtil.str2Date(time + " 00:00:01");
		Calendar scalendar = Calendar.getInstance();
		scalendar.setTime(datetmp);// 要判断的时间炄1�7
		calendarnow.add(Calendar.MONTH, interval); // 将当前日期前翻interval个月
		calendarnow.set(Calendar.DAY_OF_MONTH, 1);// 将当前日期前翻interval个月之后
		// 将日期翻到该月第丄1�7处1�7
		calendarnow.set(Calendar.HOUR_OF_DAY, 0);
		calendarnow.set(Calendar.MINUTE, 0);
		calendarnow.set(Calendar.SECOND, 0);

		if (!scalendar.after(calendarnow)) {
			t = false;
		}
		return t;
	}

	public static boolean checkDate(String date1, String date2) {

		Date date11 = DateUtil.str2Date(date1, "yyyy-MM-dd HH:mm:ss");// 起始时间

		Date date22 = DateUtil.str2Date(date2, "yyyy-MM-dd HH:mm:ss");// 终止时间

		Calendar scalendar = Calendar.getInstance();
		scalendar.setTime(date11);// 起始时间

		Calendar ecalendar = Calendar.getInstance();
		ecalendar.setTime(date22);// 终止时间

		Calendar calendarnow = Calendar.getInstance();

		System.out.println(date11.toString());
		System.out.println(date22.toString());
		System.out.println(scalendar.toString());
		System.out.println(ecalendar.toString());
		System.out.println(calendarnow.toString());

		if (calendarnow.after(scalendar) && calendarnow.before(ecalendar)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 计算两个日期相隔的天敄1�7
	 * 
	 * @param firstString
	 * @param secondString
	 * @return
	 */
	public static int nDaysBetweenTwoDate(String starttime, String endtime) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date firstDate = null;
		Date secondDate = null;
		try {
			firstDate = df.parse(starttime);
			secondDate = df.parse(endtime);
		} catch (Exception e) {
			e.printStackTrace();
			// 日期型字符串格式错误
		}
		int nDay = (int) ((secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000));
		return nDay;
	}

	/**
	 * 获取下拉时间选择桄1�7 yyyymm 只能显示仄1�709幄1�7�到今天的上丄1�7个月 yearTmp 幄1�7 monTmp 朄1�7 id select组件的id
	 * 
	 * @return
	 */
	public static String makSearchDate(String yearTmp, String monTmp, String id) {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		StringBuffer sb = new StringBuffer("<select name='" + id + "' id='" + id + "'>");
		for (int i = 2009; i <= year; i++) {
			if (i == year) {
				for (int j = 1; j <= month + 1; j++) {
					if (j < 10) {
						if (yearTmp != null && monTmp != null && Integer.parseInt(yearTmp) == year
								&& Integer.parseInt(monTmp) == j)
							sb.append("<option value='" + year + "0" + j + "' selected>" + year + "幄1�7" + j
									+ "朄1�7/option>");
						else {
							sb.append("<option value='" + year + "0" + j + "' >" + year + "幄1�7" + j + "朄1�7/option>");
						}
					} else {
						if (yearTmp != null && monTmp != null && Integer.parseInt(yearTmp) == year
								&& Integer.parseInt(monTmp) == j) {
							sb.append("<option value='" + year + "" + j + "' selected>" + year + "幄1�7" + j + "朄1�7/option>");
						} else {
							sb.append("<option value='" + year + "" + j + "'>" + year + "幄1�7" + j + "朄1�7/option>");
						}

					}
				}
			} else {
				for (int j = 1; j <= 12; j++) {
					if (j < 10) {
						if (yearTmp != null && monTmp != null && Integer.parseInt(yearTmp) == i
								&& Integer.parseInt(monTmp) == j) {
							sb.append("<option value='" + i + "0" + j + "' selected>" + i + "幄1�7" + j + "朄1�7/option>");
						} else {
							sb.append("<option value='" + i + "0" + j + "'>" + i + "幄1�7" + j + "朄1�7/option>");
						}
					} else {
						if (yearTmp != null && monTmp != null && Integer.parseInt(yearTmp) == i
								&& Integer.parseInt(monTmp) == j) {
							sb.append("<option value='" + i + "" + j + "' selected>" + i + "幄1�7" + j + "朄1�7/option>");
						} else {
							sb.append("<option value='" + i + "" + j + "'>" + i + "幄1�7" + j + "朄1�7/option>");
						}
					}
				}
			}
		}
		sb.append("</select>");
		return sb.toString();
	}
	
	/**
	 * 将数据库的格式转成参数的格式
	 * @param dbDate
	 * @return 空�1�7�的情况返回NULL
	 */
	public static String db2Para (String dbDate) {
		if (dbDate != null && !"".equals(dbDate)) {
			return dbDate.replaceAll("-", "");
		} else {
			return null;
		}
	}

	/**
	 * 获取当前时间的上num个月
	 * 
	 * @param num
	 * @return
	 */
	public static String makLastMonth(int num) {
		Calendar now = Calendar.getInstance();
		now.add(now.MONTH, num);
		return DateUtil.date2Str(now.getTime(), "yyyyMM");

	}

	public static void main(String arf[]) {

		String time1 = "2009-05-07 19:20:00";
		String time2 = "2009-05-08 19:30:00";

		DateUtil d = new DateUtil();
		// System.out.println(d.checkDate(time1, time2));
		// System.out.println(d.date2Str(new Date()));
		// System.out.println(d.checkOnly6Month("2009-03-01", -5));
		System.out.println(d.makLastMonth(-2));
		// System.out.println(d.makSearchDate("2010", "4"));
		System.out.println(d.getLastDayOfMonth(2010, 2));
	}

}
