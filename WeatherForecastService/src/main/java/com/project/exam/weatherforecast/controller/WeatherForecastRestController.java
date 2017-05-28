package com.project.exam.weatherforecast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.exam.weatherforecast.model.WeatherForecast;
import com.project.exam.weatherforecast.service.WeatherForecastService;

@RestController
public class WeatherForecastRestController {

	@Autowired
	WeatherForecastService weatherForecastService;

	@RequestMapping(value = "/wf/{location}", method = RequestMethod.GET)
	public ResponseEntity<WeatherForecast> getLocationWeatherForecastForCurrentDate(@PathVariable("location") String location) {
		WeatherForecast locationWeatherForecast = weatherForecastService.getLocationWeatherForecastForCurrentDate(location);
		if (locationWeatherForecast == null) {
			return new ResponseEntity<WeatherForecast>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<WeatherForecast>(locationWeatherForecast, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/wf/{location}/{inputDate}", method = RequestMethod.GET)
	public ResponseEntity<WeatherForecast> getLocationWeatherForecastForGivenDate(@PathVariable("location") String location, @PathVariable("inputDate") String inputDate) {
		WeatherForecast locationWeatherForecast = weatherForecastService.getLocationWeatherForecastForGivenDate(location, inputDate);
		if (locationWeatherForecast == null) {
			return new ResponseEntity<WeatherForecast>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<WeatherForecast>(locationWeatherForecast, HttpStatus.OK);
	}
}