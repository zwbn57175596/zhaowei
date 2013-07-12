package com.asiainfo.hya.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 鏃堕棿澶勭悊鍑芥暟
 * 
 * @author hxw
 */
public class DateUtil {

	private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 鑾峰彇涓�涓湀鐨勬渶鍚庝竴澶�
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
	 * 杩斿洖缁欏畾鏃堕棿鎵�鍦ㄧ殑鏈堜唤锛屼互鏁板瓧鐨勫舰寮�(201101锛�1102锛�1103.......)
	 * 
	 * @param date
	 *            浼犲叆鏃堕棿 2011-02-28
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
	 * 鑾峰彇鐩搁殧interval澶╃殑鏃堕棿 骞惰繑鍥瀙attern绫诲瀷鐨剆tring
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
	 * 灏嗗瓧绗︿覆绫诲瀷杞崲涓烘椂闂寸被鍨�
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
	 * 灏嗗瓧绗︿覆鎸夌収pattern绫诲瀷杞崲涓烘椂闂寸被鍨�
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
	 * 灏嗘椂闂存牸寮忓寲
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
	 * 灏嗘椂闂存牸寮忓寲
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
	 * 灏哾ate杞崲鎴愰粯璁ゆ椂闂存牸寮忕殑瀛楃涓�
	 * 
	 * @param date
	 * @return
	 */
	public static String date2Str(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
		return sdf.format(date);
	}

	/**
	 * 灏哾ate杞崲鎴恌ormat鏍煎紡鐨勫瓧绗︿覆
	 * 
	 * @param date
	 * @return
	 */
	public static String date2Str(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 鑾峰彇鏄ㄥぉ
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
	 * 鑾峰彇涓婂懆绗竴澶╋紙鍛ㄤ竴锛�
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
	 * 杩斿洖鎸囧畾鏈堢殑鏈�鍚庝竴澶� 姣斿 201002 鑾峰彇2鏈堢殑鏈�鍚庝竴澶�
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
	 * 鑾峰彇涓婂懆鏈�鍚庝竴澶╋紙鍛ㄦ湯锛�
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
	 * 鏀规洿鐜板湪鏃堕棿
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
	 * 鏇存敼鏃堕棿
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
	 * 姣旇緝鏃堕棿鏄惁鍦ㄨ繖涓や釜鏃堕棿鐐逛箣闂�
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean checkTime(String time1, String time2) {
		Calendar calendar = Calendar.getInstance();
		Date date1 = calendar.getTime();
		Date date11 = DateUtil.str2Date(DateUtil.date2Str(date1, "yyyy-MM-dd") + " " + time1);// 璧峰鏃堕棿

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		Date date2 = c.getTime();
		Date date22 = DateUtil.str2Date(DateUtil.date2Str(date2, "yyyy-MM-dd") + " " + time2);// 缁堟鏃堕棿

		Calendar scalendar = Calendar.getInstance();
		scalendar.setTime(date11);// 璧峰鏃堕棿

		Calendar ecalendar = Calendar.getInstance();
		ecalendar.setTime(date22);// 缁堟鏃堕棿

		Calendar calendarnow = Calendar.getInstance();

		if (calendarnow.after(scalendar) && calendarnow.before(ecalendar)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 妫�鏌ヨ緭鍏ユ棩鏈熸槸鍚︽槸interval鏈堜箣鍐呯殑鏃ユ湡
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
		scalendar.setTime(datetmp);// 瑕佸垽鏂殑鏃堕棿鐐�
		calendarnow.add(Calendar.MONTH, interval); // 灏嗗綋鍓嶆棩鏈熷墠缈籭nterval涓湀
		calendarnow.set(Calendar.DAY_OF_MONTH, 1);// 灏嗗綋鍓嶆棩鏈熷墠缈籭nterval涓湀涔嬪悗
		// 灏嗘棩鏈熺炕鍒拌鏈堢涓�澶�
		calendarnow.set(Calendar.HOUR_OF_DAY, 0);
		calendarnow.set(Calendar.MINUTE, 0);
		calendarnow.set(Calendar.SECOND, 0);

		if (!scalendar.after(calendarnow)) {
			t = false;
		}
		return t;
	}

	public static boolean checkDate(String date1, String date2) {

		Date date11 = DateUtil.str2Date(date1, "yyyy-MM-dd HH:mm:ss");// 璧峰鏃堕棿

		Date date22 = DateUtil.str2Date(date2, "yyyy-MM-dd HH:mm:ss");// 缁堟鏃堕棿

		Calendar scalendar = Calendar.getInstance();
		scalendar.setTime(date11);// 璧峰鏃堕棿

		Calendar ecalendar = Calendar.getInstance();
		ecalendar.setTime(date22);// 缁堟鏃堕棿

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
	 * 璁＄畻涓や釜鏃ユ湡鐩搁殧鐨勫ぉ鏁�
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
			// 鏃ユ湡鍨嬪瓧绗︿覆鏍煎紡閿欒
		}
		int nDay = (int) ((secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000));
		return nDay;
	}

	/**
	 * 鑾峰彇涓嬫媺鏃堕棿閫夋嫨妗� yyyymm 鍙兘鏄剧ず浠�09骞�堝埌浠婂ぉ鐨勪笂涓�涓湀 yearTmp 骞� monTmp 鏈� id select缁勪欢鐨刬d
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
							sb.append("<option value='" + year + "0" + j + "' selected>" + year + "骞�" + j
									+ "鏈�/option>");
						else {
							sb.append("<option value='" + year + "0" + j + "' >" + year + "骞�" + j + "鏈�/option>");
						}
					} else {
						if (yearTmp != null && monTmp != null && Integer.parseInt(yearTmp) == year
								&& Integer.parseInt(monTmp) == j) {
							sb.append("<option value='" + year + "" + j + "' selected>" + year + "骞�" + j + "鏈�/option>");
						} else {
							sb.append("<option value='" + year + "" + j + "'>" + year + "骞�" + j + "鏈�/option>");
						}

					}
				}
			} else {
				for (int j = 1; j <= 12; j++) {
					if (j < 10) {
						if (yearTmp != null && monTmp != null && Integer.parseInt(yearTmp) == i
								&& Integer.parseInt(monTmp) == j) {
							sb.append("<option value='" + i + "0" + j + "' selected>" + i + "骞�" + j + "鏈�/option>");
						} else {
							sb.append("<option value='" + i + "0" + j + "'>" + i + "骞�" + j + "鏈�/option>");
						}
					} else {
						if (yearTmp != null && monTmp != null && Integer.parseInt(yearTmp) == i
								&& Integer.parseInt(monTmp) == j) {
							sb.append("<option value='" + i + "" + j + "' selected>" + i + "骞�" + j + "鏈�/option>");
						} else {
							sb.append("<option value='" + i + "" + j + "'>" + i + "骞�" + j + "鏈�/option>");
						}
					}
				}
			}
		}
		sb.append("</select>");
		return sb.toString();
	}
	
	/**
	 * 灏嗘暟鎹簱鐨勬牸寮忚浆鎴愬弬鏁扮殑鏍煎紡
	 * @param dbDate
	 * @return 绌哄�肩殑鎯呭喌杩斿洖NULL
	 */
	public static String db2Para (String dbDate) {
		if (dbDate != null && !"".equals(dbDate)) {
			return dbDate.replaceAll("-", "");
		} else {
			return null;
		}
	}

	/**
	 * 鑾峰彇褰撳墠鏃堕棿鐨勪笂num涓湀
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
