package com.project.exam.weatherforecast.core.model;

public enum Statistic {
	HIGHEST, LOWEST, MEAN;

	public static Statistic getStatistic(String statistic) {
		switch (statistic.toLowerCase()) {
		case ("highest"):
			return HIGHEST;
		case ("lowest"):
			return LOWEST;
		default:
			return null;
		}
	}
}
