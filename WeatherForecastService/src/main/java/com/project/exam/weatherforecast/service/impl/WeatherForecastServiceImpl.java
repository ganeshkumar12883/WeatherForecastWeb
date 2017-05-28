package com.project.exam.weatherforecast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exam.weatherforecast.generator.WeatherForecastGeneratorServiceImpl;
import com.project.exam.weatherforecast.model.WeatherForecast;
import com.project.exam.weatherforecast.service.WeatherForecastService;

@Service("weatherForecastService")
public class WeatherForecastServiceImpl implements WeatherForecastService {

	@Autowired
	WeatherForecastGeneratorServiceImpl wfGeneratorService;

	@Override
	public WeatherForecast getLocationWeatherForecastForCurrentDate(String location) {
		return getLocationWeatherForecast(location, null);
	}

	@Override
	public WeatherForecast getLocationWeatherForecastForGivenDate(String location, String inputDate) {
		return getLocationWeatherForecast(location, inputDate);
	}

	private WeatherForecast getLocationWeatherForecast(String location, String inputDate) {

		String locationForecastData = wfGeneratorService.getWeatherForecastForLocation(location, inputDate);

		WeatherForecast locWeatherForecast = new WeatherForecast();
		String[] dataContent = locationForecastData.split("\\|");
		if (dataContent[0].equals(location)) {
			locWeatherForecast.setLocation(dataContent[0]);

			// Get location coordinates
			String[] coordinates = dataContent[1].split(",");
			locWeatherForecast.setLatitude(coordinates[0] + "\u00B0S");
			locWeatherForecast.setLongitude(coordinates[1] + "\u00B0E");
			locWeatherForecast.setElevation(coordinates[2]);

			locWeatherForecast.setLocalTime(dataContent[2]);
			locWeatherForecast.setCondition(dataContent[3]);
			locWeatherForecast.setTemperature(dataContent[4] + "\u00B0");
			locWeatherForecast.setPressure(dataContent[5]);
			locWeatherForecast.setHumidity(dataContent[6]);
		}

		return locWeatherForecast;
	}
}
