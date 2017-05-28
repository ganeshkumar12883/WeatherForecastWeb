package com.project.exam.weatherforecast.generator;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.springframework.stereotype.Service;

import com.project.exam.weatherforecast.core.generator.WeatherDataGenerator;
import com.project.exam.weatherforecast.core.model.WeatherData;
import com.project.exam.weatherforecast.core.model.WeatherDataModel;
import com.project.exam.weatherforecast.core.parser.impl.WeatherDataParserImpl;
import com.project.exam.weatherforecast.core.reader.impl.FileDataLineReader;

@Service("wfGeneratorService")
public class WeatherForecastGeneratorServiceImpl implements WeatherForecastGeneratorService {

	public String getWeatherForecastForLocation(String location, String inputDate) {

		try {
			ClassLoader classLoader = getClass().getClassLoader();
			FileDataLineReader<WeatherData> tempDataFileReader = new FileDataLineReader<>(
					new File(classLoader.getResource("temperaturedata.txt").getFile()), new WeatherDataParserImpl());

			WeatherDataModel<WeatherData> tempDataModel = new WeatherDataModel<>(tempDataFileReader);
			WeatherDataGenerator generator = new WeatherDataGenerator(tempDataModel);
			return generator.generateAllLocationWeatherForecast(location, inputDate);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
