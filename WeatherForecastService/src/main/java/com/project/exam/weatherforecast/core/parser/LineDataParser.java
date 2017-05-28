package com.project.exam.weatherforecast.core.parser;

public interface LineDataParser<T> {

	public T parseLineData(String line);
}
