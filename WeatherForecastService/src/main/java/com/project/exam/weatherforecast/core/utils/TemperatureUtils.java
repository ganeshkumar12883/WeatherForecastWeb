package com.project.exam.weatherforecast.core.utils;

import com.project.exam.weatherforecast.core.model.TemperatureModel;

public class TemperatureUtils {

	/*
	 * This model will contain the temperature recorded for current month and
	 * adjacent months - previous and next months. This data is used to
	 * calculate the temperature for any day within a month based on below
	 * formula:
	 * 
	 * The max/min temp is assumed to be captured on a specific day of every
	 * month (Ex: 15th of every month) The temp diff between two months is the
	 * drop/raise in temp from 15th to 15th of each month. The deviation value
	 * for each day can be thus calculated i.e, tempDiff/(15+15) This per day
	 * temp deviation value is calculated for the days falling after 15th of
	 * current month/previous month deviation for days until today = (deviation
	 * per day i.e, monthly deviation/30) * (currentDay + 15 i.e, 15 days of
	 * previous/current month) today temp = previous max temp - deviation for
	 * days until today
	 */
	public static double getTemperatureOfReqdDay(TemperatureModel tempModel, int requiredDay) {

		int daysInBetween = 30; // Just consider 30 days for a month i.e, no of
								// days between preMonthTemp identified date and
								// currMonthTemp identified date.

		double prevMonthTemp = tempModel.getPreMonthTemp();
		double currMonthTemp = tempModel.getCurrMonthTemp();
		double nextMonthTemp = tempModel.getNextMonthTemp();

		/*
		 * Calculate the total deviation of temperature between last month &
		 * current month and current month & next month.
		 */
		double totaldevFromPreMonth = prevMonthTemp - currMonthTemp;
		double totaldevToNextMonth = currMonthTemp - nextMonthTemp;

		// Calculate the respective deviation per day value for both values
		double perDayDevPreMonth = totaldevFromPreMonth / daysInBetween;
		double perDayDevNextMonth = totaldevToNextMonth / daysInBetween;

		/*
		 * If the requested date falls before 15th of the current month, the
		 * reqd temp will have to be calculated with the previous month recorded
		 * temperature If the requested date falls after 15th of the current
		 * month, the reqd temp will have to be calculated with the current
		 * month recorded temperature
		 */
		double reqdDayTemp;
		if (requiredDay < (daysInBetween / 2)) {
			// Before the 15th of the month
			double totalDevUntilReqDay = perDayDevPreMonth * (daysInBetween / 2 + requiredDay);
			reqdDayTemp = prevMonthTemp - totalDevUntilReqDay;
		} else {
			// After 15th of the month
			double totalDevUntilReqDay = perDayDevNextMonth * (requiredDay - daysInBetween / 2);
			reqdDayTemp = currMonthTemp + totalDevUntilReqDay;
		}

		return reqdDayTemp;
	}

	public static double getTemperatureOfSpecificTimeOfReqdDay(double dayMaxTemp, double dayMinTemp,
			String requiredDayTime) {

		double perHrDev = (dayMaxTemp - dayMinTemp) / 12;

		// process time string to hours and mins in 24 hr format
		int reqdHrs = 0;
		int hrsBetween = 12 * 100;
		if (requiredDayTime != null) {
			int hour = 0, mins = 0;
			String[] timeSplitFromHour = requiredDayTime.split(":");
			if (timeSplitFromHour != null && timeSplitFromHour.length == 2) {
				hour = Integer.valueOf(timeSplitFromHour[0]);
				String[] timeSplitFromMin = timeSplitFromHour[1].split(" ");
				if (timeSplitFromMin != null && timeSplitFromMin.length == 2) {
					mins = Integer.valueOf(timeSplitFromMin[0]);
					if (timeSplitFromMin[1].equals("PM")) {
						hour = hour + 12;
					}
				}
			}
			reqdHrs = hour * 100 + mins;
		}

		if (reqdHrs == hrsBetween) {
			return dayMaxTemp;
		}

		if (reqdHrs < hrsBetween) {
			return dayMinTemp + (perHrDev * (reqdHrs / 100));
		}

		if (reqdHrs > hrsBetween) {
			return dayMaxTemp - (perHrDev * ((reqdHrs - hrsBetween) / 100));
		}

		return 0;
	}

}
