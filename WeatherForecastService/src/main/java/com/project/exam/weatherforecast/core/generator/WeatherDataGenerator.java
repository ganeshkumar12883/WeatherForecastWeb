package com.project.exam.weatherforecast.core.generator;

import java.text.ParseException;
import java.util.Map;
import java.util.Map.Entry;

import com.project.exam.weatherforecast.core.model.Statistic;
import com.project.exam.weatherforecast.core.model.TemperatureModel;
import com.project.exam.weatherforecast.core.model.WeatherDataModel;
import com.project.exam.weatherforecast.core.utils.ConditionsUtils;
import com.project.exam.weatherforecast.core.utils.DateUtils;
import com.project.exam.weatherforecast.core.utils.HumidityUtils;
import com.project.exam.weatherforecast.core.utils.PressureUtils;
import com.project.exam.weatherforecast.core.utils.RandomGenerator;
import com.project.exam.weatherforecast.core.utils.TemperatureUtils;

public class WeatherDataGenerator {

	private WeatherDataModel<?> weatherDataModel;

	public WeatherDataGenerator(WeatherDataModel<?> weatherDataModel) {
		this.weatherDataModel = weatherDataModel;
	}

	public String generateAllLocationWeatherForecast(String location, String inputDateStr) throws ParseException {
		
		// If input date is passed as null, we have to get the default current date
		inputDateStr = DateUtils.checkInputAndGetCurrentDateStr(inputDateStr);
		
		int ipDateMonth = DateUtils.getMonthOfInputDateString(inputDateStr);
		int ipDateDay = DateUtils.getDayOfInputDateString(inputDateStr);

		Map<String, Map<Integer, Map<Statistic, TemperatureModel>>> locTempDataModelMap = weatherDataModel
				.getLocTempDataModelMap();
		for (Entry<String, Map<Integer, Map<Statistic, TemperatureModel>>> locTempDataModel : locTempDataModelMap
				.entrySet()) {

			String locTempDataModelKey = locTempDataModel.getKey();
			String[] keyElements = locTempDataModelKey.split("\\|");
			String entryLocation = keyElements[0];
			if (!(entryLocation.equals(location))) {
				continue;
			}
			
			String entryCoordinates = keyElements[1];
			
			Map<Integer, Map<Statistic, TemperatureModel>> monTempDataModelMap = locTempDataModel.getValue();

			// Determine temperature for the input date - both max and min temp for the day
			TemperatureModel maxTempModel = monTempDataModelMap.get(ipDateMonth).get(Statistic.HIGHEST);
			double dayMaxTemp = TemperatureUtils.getTemperatureOfReqdDay(maxTempModel, ipDateDay);
			
			TemperatureModel minTempModel = monTempDataModelMap.get(ipDateMonth).get(Statistic.LOWEST);
			double dayMinTemp = TemperatureUtils.getTemperatureOfReqdDay(minTempModel, ipDateDay);
			
			/*
			 * Now that we got the maximum and minimum temperature for a day,
			 * the temperature can be observed in below pattern Maximum in
			 * mid-noon and minimum during nights, other hours it would be
			 * within the boundary So if input time is given, we can calculate
			 * for specific time, else the result will go for three random hours
			 * of the day 9 AM, 12 PM, 3 PM, 6 PM, 9 PM, 12 AM
			 */
			double reqdTempOfDayTime = TemperatureUtils.getTemperatureOfSpecificTimeOfReqdDay(dayMaxTemp, dayMinTemp,
					RandomGenerator.getRandomNumber(7, 12) + " AM");
			
			//Determine the time string to display in result as per input
			String ipDateTimeStr = null;
			try {
				ipDateTimeStr = DateUtils.getDateTimeStrOfInputValues(inputDateStr);
			} catch (ParseException e) {
				System.out.println("Parse Exception while parsing date " + e);
			}
			
			// Determine the condition based on the temperature
			String condition = ConditionsUtils.getConditionsOfTheDay(reqdTempOfDayTime, ipDateMonth);

			// Determine the humidity based on the condition
			int humidity = HumidityUtils.getHumidityForCondition(condition);

			// Determine the pressure based on the temperature. Get the elevation from key
			String elevation = entryCoordinates
					.substring(entryCoordinates.lastIndexOf(",") + 1, entryCoordinates.length()).trim();
			String pressure = PressureUtils.calculatePressureForTheDay(reqdTempOfDayTime, Integer.valueOf(elevation));

			// Convert temperature value to two digits
			String formattedTemperature = String.format("%.2f", reqdTempOfDayTime).toString();

			StringBuilder outputBuilder = new StringBuilder();
			outputBuilder.append(locTempDataModelKey).append("|").append(ipDateTimeStr).append("|").append(condition)
					.append("|").append(formattedTemperature).append("|").append(pressure).append("|").append(humidity);
			
			return outputBuilder.toString();
		}
		
		return null;

	}

	public WeatherDataModel<?> getWeatherDataModel() {
		return weatherDataModel;
	}

	public void setWeatherDataModel(WeatherDataModel<?> weatherDataModel) {
		this.weatherDataModel = weatherDataModel;
	}

}
