package com.project.exam.weatherforecast.core.model;

public class TemperatureModel {

	private final double preMonthTemp;

	private final double currMonthTemp;

	private final double nextMonthTemp;

	public TemperatureModel(double preMonthTemp, double currMonthTemp, double nextMonthTemp) {
		this.preMonthTemp = preMonthTemp;
		this.currMonthTemp = currMonthTemp;
		this.nextMonthTemp = nextMonthTemp;
	}

	public double getPreMonthTemp() {
		return preMonthTemp;
	}

	public double getCurrMonthTemp() {
		return currMonthTemp;
	}

	public double getNextMonthTemp() {
		return nextMonthTemp;
	}

}
