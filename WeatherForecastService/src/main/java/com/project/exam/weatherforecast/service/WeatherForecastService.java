package com.project.exam.weatherforecast.service;

import com.project.exam.weatherforecast.model.WeatherForecast;

public interface WeatherForecastService {

	public WeatherForecast getLocationWeatherForecastForCurrentDate(String location);
	
	public WeatherForecast getLocationWeatherForecastForGivenDate(String location, String inputDate);

}
