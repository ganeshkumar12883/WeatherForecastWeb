package com.project.exam.weatherforecast;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.project.exam.weatherforecast.core.model.TemperatureModel;
import com.project.exam.weatherforecast.core.utils.ConditionsUtils;
import com.project.exam.weatherforecast.core.utils.HumidityUtils;
import com.project.exam.weatherforecast.core.utils.PressureUtils;
import com.project.exam.weatherforecast.core.utils.TemperatureUtils;

public class ForecastUtilsTest {

	@Test
	public void testTempertureUtils() {
		TemperatureModel tempModel = new TemperatureModel(22.50, 25.0, 27.50);

		// Test for day in first 15 days of the month
		double tempOfDayBefore = TemperatureUtils.getTemperatureOfReqdDay(tempModel, 12);
		assertTrue("Equals", 24.75 - Double.valueOf(String.format("%.2f", tempOfDayBefore).toString()) == 0);

		// test for day in second 15 days of the month
		double tempOfDayAfter = TemperatureUtils.getTemperatureOfReqdDay(tempModel, 22);
		assertTrue("Equals", 24.42 - Double.valueOf(String.format("%.2f", tempOfDayAfter).toString()) == 0);
	}

	@Test
	public void ConditionsUtils() {
		String rainCondition = ConditionsUtils.getConditionsOfTheDay(10.5, 7);
		assertNotNull(rainCondition);
		assertTrue("Rain".equals(rainCondition));

		String snowCondition = ConditionsUtils.getConditionsOfTheDay(-2.5, 6);
		assertNotNull(snowCondition);
		assertTrue("Snow".equals(snowCondition));

		String sunnyCondition = ConditionsUtils.getConditionsOfTheDay(20.5, 13);
		assertNotNull(sunnyCondition);
		assertTrue("Sunny".equals(sunnyCondition));
	}

	@Test
	public void PressureUtils() {
		String lowPressure = PressureUtils.calculatePressureForTheDay(20, 150);
		assertTrue("985.0".equals(lowPressure));

		String highPressure = PressureUtils.calculatePressureForTheDay(-2, 300);
		assertTrue("1030.0".equals(highPressure));
	}

	@Test
	public void HumidityUtils() {
		int highHumidity = HumidityUtils.getHumidityForCondition("Rain");
		assertTrue(highHumidity >= 85 && highHumidity <= 100);

		int medHumidity = HumidityUtils.getHumidityForCondition("Snow");
		assertTrue(medHumidity >= 60 && medHumidity <= 75);

		int lowHumidity = HumidityUtils.getHumidityForCondition("Sunny");
		assertTrue(lowHumidity >= 35 && lowHumidity <= 50);
	}

}
