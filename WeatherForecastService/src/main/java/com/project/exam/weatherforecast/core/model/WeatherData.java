package com.project.exam.weatherforecast.core.model;

public class WeatherData implements WeatherDataMapper {

	// The values are constant for each location in the input. They are handled
	// as string since long & lat will be with direction notations as well
	private String location;

	private String latitude;

	private String longitude;

	private String elevation;

	private Statistic statistic;

	// Here the monthly data is held as double value since they are decimal values

	private double janData;

	private double febData;

	private double marData;

	private double aprData;

	private double mayData;

	private double junData;

	private double julData;

	private double augData;

	private double sepData;

	private double octData;

	private double novData;

	private double decData;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getElevation() {
		return elevation;
	}

	public void setElevation(String elevation) {
		this.elevation = elevation;
	}

	public Statistic getStatistic() {
		return statistic;
	}

	public void setStatistic(Statistic statistic) {
		this.statistic = statistic;
	}

	public double getJanData() {
		return janData;
	}

	public void setJanData(double janData) {
		this.janData = janData;
	}

	public double getFebData() {
		return febData;
	}

	public void setFebData(double febData) {
		this.febData = febData;
	}

	public double getMarData() {
		return marData;
	}

	public void setMarData(double marData) {
		this.marData = marData;
	}

	public double getAprData() {
		return aprData;
	}

	public void setAprData(double aprData) {
		this.aprData = aprData;
	}

	public double getMayData() {
		return mayData;
	}

	public void setMayData(double mayData) {
		this.mayData = mayData;
	}

	public double getJunData() {
		return junData;
	}

	public void setJunData(double junData) {
		this.junData = junData;
	}

	public double getJulData() {
		return julData;
	}

	public void setJulData(double julData) {
		this.julData = julData;
	}

	public double getAugData() {
		return augData;
	}

	public void setAugData(double augData) {
		this.augData = augData;
	}

	public double getSepData() {
		return sepData;
	}

	public void setSepData(double sepData) {
		this.sepData = sepData;
	}

	public double getOctData() {
		return octData;
	}

	public void setOctData(double octData) {
		this.octData = octData;
	}

	public double getNovData() {
		return novData;
	}

	public void setNovData(double novData) {
		this.novData = novData;
	}

	public double getDecData() {
		return decData;
	}

	public void setDecData(double decData) {
		this.decData = decData;
	}

}
