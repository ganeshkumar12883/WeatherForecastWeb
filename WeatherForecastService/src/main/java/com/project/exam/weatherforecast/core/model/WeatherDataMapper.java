package com.project.exam.weatherforecast.core.model;

public interface WeatherDataMapper {
	
	public String getLocation();

	public String getLatitude();

	public String getLongitude();

	public String getElevation();

	public Statistic getStatistic();

	public double getJanData();

	public double getFebData();

	public double getMarData();

	public double getAprData();

	public double getMayData();

	public double getJunData();

	public double getJulData();

	public double getAugData();

	public double getSepData();

	public double getOctData();

	public double getNovData();

	public double getDecData();

}
