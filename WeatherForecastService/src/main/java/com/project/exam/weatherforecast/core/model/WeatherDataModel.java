package com.project.exam.weatherforecast.core.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.project.exam.weatherforecast.core.reader.impl.FileDataLineReader;

public class WeatherDataModel<T extends WeatherDataMapper> {

	private final FileDataLineReader<T> wdReader;

	private Map<String, Map<Integer, Map<Statistic, TemperatureModel>>> locTempDataModelMap = new HashMap<>();

	public WeatherDataModel(FileDataLineReader<T> wdReader) {
		this.wdReader = wdReader;
		loadWeatherDataFromFiles();
	}

	public void loadWeatherDataFromFiles() {

		T readDataObj;

		while ((readDataObj = readFileData()) != null) {
			StringBuilder locDataModelKeyBuilder = new StringBuilder();
			locDataModelKeyBuilder.append(readDataObj.getLocation()).append("|").append(readDataObj.getLatitude())
					.append(",").append(readDataObj.getLongitude()).append(",").append(readDataObj.getElevation());
			String location = locDataModelKeyBuilder.toString();

			if (!locTempDataModelMap.containsKey(location)) {
				locTempDataModelMap.put(location, new HashMap<Integer, Map<Statistic, TemperatureModel>>());
			}
			Map<Integer, Map<Statistic, TemperatureModel>> monTempDataModel = locTempDataModelMap.get(location);

			// Jan Month Data
			if (!monTempDataModel.containsKey(1)) {
				monTempDataModel.put(1, new HashMap<Statistic, TemperatureModel>());
			}
			monTempDataModel.get(1).put(readDataObj.getStatistic(),
					new TemperatureModel(readDataObj.getDecData(), readDataObj.getJanData(), readDataObj.getFebData()));

			// Feb Month Data
			if (!monTempDataModel.containsKey(2)) {
				monTempDataModel.put(2, new HashMap<Statistic, TemperatureModel>());
			}
			monTempDataModel.get(2).put(readDataObj.getStatistic(),
					new TemperatureModel(readDataObj.getJanData(), readDataObj.getFebData(), readDataObj.getMarData()));

			// Jan Month Data
			if (!monTempDataModel.containsKey(3)) {
				monTempDataModel.put(3, new HashMap<Statistic, TemperatureModel>());
			}
			monTempDataModel.get(3).put(readDataObj.getStatistic(),
					new TemperatureModel(readDataObj.getFebData(), readDataObj.getMarData(), readDataObj.getAprData()));

			// Jan Month Data
			if (!monTempDataModel.containsKey(4)) {
				monTempDataModel.put(4, new HashMap<Statistic, TemperatureModel>());
			}
			monTempDataModel.get(4).put(readDataObj.getStatistic(),
					new TemperatureModel(readDataObj.getMarData(), readDataObj.getAprData(), readDataObj.getMayData()));

			// Jan Month Data
			if (!monTempDataModel.containsKey(5)) {
				monTempDataModel.put(5, new HashMap<Statistic, TemperatureModel>());
			}
			monTempDataModel.get(5).put(readDataObj.getStatistic(),
					new TemperatureModel(readDataObj.getAprData(), readDataObj.getMayData(), readDataObj.getJunData()));

			// Jan Month Data
			if (!monTempDataModel.containsKey(6)) {
				monTempDataModel.put(6, new HashMap<Statistic, TemperatureModel>());
			}
			monTempDataModel.get(6).put(readDataObj.getStatistic(),
					new TemperatureModel(readDataObj.getMayData(), readDataObj.getJunData(), readDataObj.getJulData()));

			// Jan Month Data
			if (!monTempDataModel.containsKey(7)) {
				monTempDataModel.put(7, new HashMap<Statistic, TemperatureModel>());
			}
			monTempDataModel.get(7).put(readDataObj.getStatistic(),
					new TemperatureModel(readDataObj.getJunData(), readDataObj.getJulData(), readDataObj.getAugData()));

			// Jan Month Data
			if (!monTempDataModel.containsKey(8)) {
				monTempDataModel.put(8, new HashMap<Statistic, TemperatureModel>());
			}
			monTempDataModel.get(8).put(readDataObj.getStatistic(),
					new TemperatureModel(readDataObj.getJulData(), readDataObj.getAugData(), readDataObj.getSepData()));

			// Jan Month Data
			if (!monTempDataModel.containsKey(9)) {
				monTempDataModel.put(9, new HashMap<Statistic, TemperatureModel>());
			}
			monTempDataModel.get(9).put(readDataObj.getStatistic(),
					new TemperatureModel(readDataObj.getAugData(), readDataObj.getSepData(), readDataObj.getOctData()));

			// Jan Month Data
			if (!monTempDataModel.containsKey(10)) {
				monTempDataModel.put(10, new HashMap<Statistic, TemperatureModel>());
			}
			monTempDataModel.get(10).put(readDataObj.getStatistic(),
					new TemperatureModel(readDataObj.getSepData(), readDataObj.getOctData(), readDataObj.getNovData()));

			// Jan Month Data
			if (!monTempDataModel.containsKey(11)) {
				monTempDataModel.put(11, new HashMap<Statistic, TemperatureModel>());
			}
			monTempDataModel.get(11).put(readDataObj.getStatistic(),
					new TemperatureModel(readDataObj.getOctData(), readDataObj.getNovData(), readDataObj.getDecData()));

			// Jan Month Data
			if (!monTempDataModel.containsKey(12)) {
				monTempDataModel.put(12, new HashMap<Statistic, TemperatureModel>());
			}
			monTempDataModel.get(12).put(readDataObj.getStatistic(),
					new TemperatureModel(readDataObj.getNovData(), readDataObj.getDecData(), readDataObj.getJanData()));

		}
	}

	public Map<String, Map<Integer, Map<Statistic, TemperatureModel>>> getLocTempDataModelMap() {
		return locTempDataModelMap;
	}

	public void setLocTempDataModelMap(
			Map<String, Map<Integer, Map<Statistic, TemperatureModel>>> locTempDataModelMap) {
		this.locTempDataModelMap = locTempDataModelMap;
	}

	private T readFileData() {
		try {
			return wdReader.readFileData();
		} catch (IOException e) {
			throw new RuntimeException("Error reading the line of the file");
		}
	}

	public FileDataLineReader<T> getWdReader() {
		return wdReader;
	}

}
