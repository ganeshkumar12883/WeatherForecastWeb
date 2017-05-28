package com.project.exam.weatherforecast.core.parser.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.project.exam.weatherforecast.core.model.Statistic;
import com.project.exam.weatherforecast.core.model.WeatherData;
import com.project.exam.weatherforecast.core.parser.LineDataParser;

public class WeatherDataParserImpl implements LineDataParser<WeatherData> {

	private static final int LINE_ELEMENTS_COUNT = 17;

	private static final String SPLIT_CHAR_COMMA = ",";

	@Override
	public WeatherData parseLineData(String line) {

		if (line == null)
			return null;

		WeatherData weatherData = new WeatherData();

		// Get the elements in the line data
		List<String> lineElements = getElementsOfLine(line);
		String locationName = lineElements.get(0);
		if (lineElements.size() == LINE_ELEMENTS_COUNT) {
			weatherData.setLocation(locationName);
			weatherData.setLatitude(lineElements.get(1));
			weatherData.setLongitude(lineElements.get(2));
			weatherData.setElevation(lineElements.get(3));

			/*
			 * Set the statistic based on the input line of data. We don't have
			 * to proceed with this data if we don't have valid statistic
			 */
			String statistic = lineElements.get(4);
			if (Statistic.getStatistic(statistic) == null)
				return null;
			weatherData.setStatistic(Statistic.getStatistic(statistic));

			/*
			 * The monthly temperature/rainfall values are as double precision
			 * values in input file. However to make code understanding easy for
			 * mathematical calculations, we can convert them to int value
			 * (assumption is there are only 2 decimal digits in the input)
			 */
			weatherData.setJanData(Double.valueOf(lineElements.get(5)));
			weatherData.setFebData(Double.valueOf(lineElements.get(6)));
			weatherData.setMarData(Double.valueOf(lineElements.get(7)));
			weatherData.setAprData(Double.valueOf(lineElements.get(8)));
			weatherData.setMayData(Double.valueOf(lineElements.get(9)));
			weatherData.setJunData(Double.valueOf(lineElements.get(10)));
			weatherData.setJulData(Double.valueOf(lineElements.get(11)));
			weatherData.setAugData(Double.valueOf(lineElements.get(12)));
			weatherData.setSepData(Double.valueOf(lineElements.get(13)));
			weatherData.setOctData(Double.valueOf(lineElements.get(14)));
			weatherData.setNovData(Double.valueOf(lineElements.get(15)));
			weatherData.setDecData(Double.valueOf(lineElements.get(16)));
		} else {
			throw new RuntimeException(
					"The line of data for " + locationName + "does not contain proper content to parse");
		}

		return weatherData;
	}

	private List<String> getElementsOfLine(String line) {
		String[] lineElements = line.split(SPLIT_CHAR_COMMA);
		if (lineElements != null) {
			return Collections.unmodifiableList(Arrays.asList(lineElements));
		}
		return null;
	}

}
