package com.project.exam.weatherforecast.core.utils;

public class PressureUtils {

	/*
	 * Ref: https://www.mide.com/pages/air-pressure-at-altitude-calculator. Appx
	 * pressure measurements in kPa (1 hPa = 1/10 kPa) at std temp of 14.85 C
	 * Elevation--hPa > 0--1013; 150--995, 300--977, 450--960, 600--942,
	 * 750--925 Since P/T=k we assume here k=0.8 , thus P = 0.8*(rec.temp -
	 * Std.temp)
	 */

	public static String calculatePressureForTheDay(double dayTemp, int elevation) {

		/*
		 * Elevated pressure = ipElevation / stdElvDiffForDeviation *
		 * stdPressureDev Here stdElvDiffForDeviation = 150m & stdPressureDev =
		 * 15
		 */
		double elevatedPressure = elevation / 150.0 * 15.0;
		int stdPressure = 1000;
		if (dayTemp < 0) {
			return String.valueOf(stdPressure + elevatedPressure);
		} else {
			return String.valueOf(stdPressure - elevatedPressure);
		}
	}

}
