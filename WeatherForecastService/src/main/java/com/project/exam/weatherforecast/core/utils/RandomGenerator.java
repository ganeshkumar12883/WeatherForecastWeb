package com.project.exam.weatherforecast.core.utils;

import java.util.Random;

public class RandomGenerator {
	
	public static int getRandomNumber(int min, int max) {
		Random randomGen = new Random();
		int randomNumber = randomGen.nextInt((max - min) + 1) + min;
		return randomNumber;
	}
}
