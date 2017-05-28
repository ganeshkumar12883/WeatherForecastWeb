package com.project.exam.weatherforecast.core.utils;

public class ConditionsUtils {

	public static String getConditionsOfTheDay(double dayTemp, int ipDateMonth) {

		/*
		 * We can consider the case where rain rains periodically across the
		 * year. Say rain/snow is observed in winter - June to Aug. If temp<0
		 * Snow else Rain. Other days are marked as Rain if temp<10 else Sunny
		 * 
		 */
		if (ipDateMonth <= 8 && ipDateMonth >= 6) {
			// This is rainy season
			return (dayTemp < 0 ? "Snow" : "Rain");
		} else {
			return (dayTemp < 15 ? "Rain" : "Sunny");
		}
	}

}
