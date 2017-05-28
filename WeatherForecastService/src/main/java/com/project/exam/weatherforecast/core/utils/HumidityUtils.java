package com.project.exam.weatherforecast.core.utils;

public class HumidityUtils {

	/*
	 * Humidity will be higher during rainy season snow season. So humidity
	 * order goes as rain>snow>sunny
	 */
	public static int getHumidityForCondition(String condition) {

		switch (condition) {
		case "Rain":
			return RandomGenerator.getRandomNumber(85, 100);
		case "Snow":
			return RandomGenerator.getRandomNumber(60, 75);
		case "Sunny":
			return RandomGenerator.getRandomNumber(35, 50);
		}
		return 0;
	}
}
