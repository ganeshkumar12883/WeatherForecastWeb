package com.project.exam.weatherforecast;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.project.exam.weatherforecast.generator.WeatherForecastGeneratorServiceImpl;
import com.project.exam.weatherforecast.service.WeatherForecastService;
import com.project.exam.weatherforecast.service.impl.WeatherForecastServiceImpl;

public class WeatherForecastGeneratorServiceTest {

	WeatherForecastService wfService = new WeatherForecastServiceImpl();
	
	WeatherForecastGeneratorServiceImpl wfGeneratorService = new WeatherForecastGeneratorServiceImpl();

	@Test
	public void testSydneyLocationWeatherForecast() {
		String locForecast = wfGeneratorService.getWeatherForecastForLocation("Sydney", null);
		assertNotNull(locForecast);
		assertTrue(locForecast.contains("Sydney"));
	}
	
	@Test
	public void testDarwinLocationWeatherForecast() {
		String locForecast = wfGeneratorService.getWeatherForecastForLocation("Darwin", "12-12-2016");
		assertNotNull(locForecast);
		assertTrue(locForecast.contains("Darwin"));
	}

}
