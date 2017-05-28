package com.project.exam.weatherforecast.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	public static String checkInputAndGetCurrentDateStr(String inputDateStr) throws ParseException {
		Date date = checkInputAndGetDate(inputDateStr);
		return sdf.format(date);
	}

	private static Date checkInputAndGetDate(String inputDateStr) throws ParseException {
		if (inputDateStr == null) {
			return new Date();
		} else {
			return sdf.parse(inputDateStr);
		}
	}

	public static int getMonthOfInputDateString(String inputDateStr) {
		int ipDateMonth = 0;
		try {
			Date inputDate = checkInputAndGetDate(inputDateStr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(inputDate);
			ipDateMonth = calendar.get(Calendar.MONTH);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ipDateMonth+1;
	}

	public static int getDayOfInputDateString(String inputDateStr) {
		int ipDateMonth = 0;
		try {
			Date inputDate = checkInputAndGetDate(inputDateStr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(inputDate);
			ipDateMonth = calendar.get(Calendar.DATE);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ipDateMonth;
	}

	public static String getDateTimeStrOfInputValues(String ipDateStr) throws ParseException {
		String reqdDateTimeStr = null;
		SimpleDateFormat ipSdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
		SimpleDateFormat opSdf = new SimpleDateFormat("YYYY-dd-MM'T'HH:mm:ss'Z'");
		opSdf.setTimeZone(TimeZone.getTimeZone("UTC"));

		String fullIpDateTime = null;
		if (ipDateStr == null) {
			fullIpDateTime = ipSdf.format(new Date());
		} else {
			fullIpDateTime = ipDateStr + " " + ("10:10:01 AM");
		}

		Date ipDateTime = ipSdf.parse(fullIpDateTime);
		reqdDateTimeStr = opSdf.format(ipDateTime);

		return reqdDateTimeStr;
	}

}
